package com.example.rps.model;

import jakarta.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int playerWins;
    private int computerWins;
    private int draws;

    public Score() {}

    public int getPlayerWins() { return playerWins; }
    public int getComputerWins() { return computerWins; }
    public int getDraws() { return draws; }

    public void incrementPlayerWins() { this.playerWins++; }
    public void incrementComputerWins() { this.computerWins++; }
    public void incrementDraws() { this.draws++; }
}
