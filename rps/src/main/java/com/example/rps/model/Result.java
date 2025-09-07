package com.example.rps.model;

public class Result {
    private String playerMove;
    private String computerMove;
    private String outcome;

    public Result(String playerMove, String computerMove, String outcome) {
        this.playerMove = playerMove;
        this.computerMove = computerMove;
        this.outcome = outcome;
    }

    public String getPlayerMove() { return playerMove; }
    public String getComputerMove() { return computerMove; }
    public String getOutcome() { return outcome; }
}

