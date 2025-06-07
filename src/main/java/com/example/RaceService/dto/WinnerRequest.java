package com.example.RaceService.dto;

import lombok.Data;

@Data
public class WinnerRequest {
    private Long raceId;
    private String winner;
}
