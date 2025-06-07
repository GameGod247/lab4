package com.example.bet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BetPublicDto {
    private Long id;
    private Long raceId;
    private String horse;
    private double amount;
    private double win;
}
