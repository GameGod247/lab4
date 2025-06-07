package com.example.odds.service;

import com.example.odds.model.Odds;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.*;

@Service
public class OddsService {

    private final Map<Long, List<Odds>> oddsMap = new HashMap<>();

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean raceExists(Long raceId) {
        try {
            String url = "http://localhost:8081/races/" + raceId;
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public List<Odds> setOdds(Long raceId, List<Odds> newOdds) {
        for (Odds o : newOdds) {
            o.setRaceId(raceId);
        }
        oddsMap.put(raceId, newOdds);
        return newOdds;
    }

    public List<Odds> getOdds(Long raceId) {
        return oddsMap.getOrDefault(raceId, new ArrayList<>());
    }
}
