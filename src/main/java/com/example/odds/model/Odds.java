package com.example.odds.model;

import lombok.Data;

@Data
public class Odds {
    private Long raceId;
    private String horse;
    private double odds;
}
