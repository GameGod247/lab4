package com.example.bet.model;

import lombok.Data;

@Data
public class Bet {
    private Long id;
    private Long raceId;
    private String horse;
    private double amount;
    private String clientName;
    private double Win;
}
