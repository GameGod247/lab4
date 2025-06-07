package com.example.odds.controller;

import com.example.odds.model.Odds;
import com.example.odds.service.OddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odds")
public class OddsController {

    @Autowired
    private OddsService oddsService;

    @PostMapping
    public ResponseEntity<?> setOdds(@RequestBody List<Odds> odds) {
        if (odds.isEmpty()) {
            return ResponseEntity.badRequest().body("Odds list cannot be empty.");
        }

        Long raceId = odds.get(0).getRaceId();
        if (raceId == null) {
            return ResponseEntity.badRequest().body("raceId must be provided in each Odds object.");
        }

        // Перевірка, що всі елементи мають однаковий raceId
        for (Odds o : odds) {
            if (!raceId.equals(o.getRaceId())) {
                return ResponseEntity.badRequest().body("All Odds must have the same raceId.");
            }
        }

        if (!oddsService.raceExists(raceId)) {
            return ResponseEntity.badRequest().body("Race with ID " + raceId + " does not exist.");
        }

        return ResponseEntity.ok(oddsService.setOdds(raceId, odds));
    }

    @GetMapping
    public ResponseEntity<?> getOdds(@RequestParam Long raceId) {
        if (!oddsService.raceExists(raceId)) {
            return ResponseEntity.badRequest().body("Race with ID " + raceId + " does not exist.");
        }
        return ResponseEntity.ok(oddsService.getOdds(raceId));
    }

}
