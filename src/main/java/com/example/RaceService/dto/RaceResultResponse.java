package com.example.RaceService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class RaceResultResponse {
    private Long raceId;
    private String raceName;
    private String winner;
    private List<WinnerInfo> winners;
    private String message;
}
