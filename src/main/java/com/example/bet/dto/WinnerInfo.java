package com.example.bet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WinnerInfo {
    private Long betId;
    private String clientName;
    private double amount;
    private double winAmount;
}
