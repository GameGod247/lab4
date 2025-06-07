package com.example.RaceService.model;

public class Bet {
    private Long id;
    private Long raceId;
    private String horse;
    private double amount;
    private double Win;

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRaceId() { return raceId; }
    public void setRaceId(Long raceId) { this.raceId = raceId; }

    public String getHorse() { return horse; }
    public void setHorse(String horse) { this.horse = horse; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getWin() { return Win; }
    public void setWin(double Win) { this.Win = Win; }
}
