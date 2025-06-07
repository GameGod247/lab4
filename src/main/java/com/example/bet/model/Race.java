package com.example.bet.model;

import java.util.List;

public class Race {
    private Long id;
    private List<String> horses;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<String> getHorses() { return horses; }
    public void setHorses(List<String> horses) { this.horses = horses; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
