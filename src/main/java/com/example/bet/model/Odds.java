package com.example.bet.model;

public class Odds {
    private Long raceId;
    private String horse;
    private double odds;

    public Long getRaceId() { return raceId; }
    public void setRaceId(Long raceId) { this.raceId = raceId; }

    public String getHorse() { return horse; }
    public void setHorse(String horse) { this.horse = horse; }

    public double getOdds() { return odds; }
    public void setOdds(double odds) { this.odds = odds; }
}
