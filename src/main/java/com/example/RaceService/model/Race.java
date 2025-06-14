package com.example.RaceService.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Race {
    private Long id;
    private String name;
    private LocalDateTime date;
    private List<String> horses;
    private String status; // PENDING, FINISHED
    private String winner;
}
