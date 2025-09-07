package com.example.rps.service;

import com.example.rps.model.Move;
import com.example.rps.model.Result;
import com.example.rps.model.Score;
import com.example.rps.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RpsService {

    private final Random random = new Random();
    private final ScoreRepository scoreRepository;

    public RpsService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Result play(String playerMoveStr) {
        Move playerMove;
        try {
            playerMove = Move.valueOf(playerMoveStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new Result(playerMoveStr, "N/A", "Invalid move!");
        }

        Move computerMove = Move.values()[random.nextInt(3)];
        String outcome = determineWinner(playerMove, computerMove);

        // Update scoreboard
        Score score = getOrCreateScore();
        switch (outcome) {
            case "You Win!" -> score.incrementPlayerWins();
            case "Computer Wins!" -> score.incrementComputerWins();
            case "Draw!" -> score.incrementDraws();
        }
        scoreRepository.save(score);

        return new Result(playerMove.name(), computerMove.name(), outcome);
    }

    public Score getOrCreateScore() {
        return scoreRepository.findAll().stream().findFirst()
                .orElseGet(() -> scoreRepository.save(new Score()));
    }

    private String determineWinner(Move player, Move computer) {
        if (player == computer) return "Draw!";
        return switch (player) {
            case ROCK -> (computer == Move.SCISSORS) ? "You Win!" : "Computer Wins!";
            case PAPER -> (computer == Move.ROCK) ? "You Win!" : "Computer Wins!";
            case SCISSORS -> (computer == Move.PAPER) ? "You Win!" : "Computer Wins!";
        };
    }

    public void resetScoreboard() {
        scoreRepository.deleteAll();
    }
}

