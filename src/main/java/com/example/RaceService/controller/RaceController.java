package com.example.RaceService.controller;

import com.example.RaceService.dto.RaceResultResponse;
import com.example.RaceService.dto.WinnerInfo;
import com.example.RaceService.dto.WinnerRequest;
import com.example.RaceService.model.Bet;
import com.example.RaceService.model.Race;
import com.example.RaceService.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/races")
public class RaceController {
    @Autowired
    private RaceService raceService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping
    public Race createRace(@RequestBody Race race) {
        return raceService.createRace(race);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRace(@PathVariable Long id) {
        Race race = raceService.getRace(id);
        return race != null ? ResponseEntity.ok(race) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable Long id, @RequestBody Race race) {
        Race updated = raceService.updateRace(id, race);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        raceService.deleteRace(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/result")
    public ResponseEntity<?> setRaceResult(@RequestBody WinnerRequest request) {
        try {
            Race updatedRace = raceService.setResult(request.getRaceId(), request.getWinner());

            // Звертаємося до BetService
            String betServiceUrl = "http://localhost:8083/bets/winners?raceId=" +
                    request.getRaceId() + "&winner=" + request.getWinner();

            ResponseEntity<WinnerInfo[]> response = restTemplate.getForEntity(betServiceUrl, WinnerInfo[].class);
            WinnerInfo[] winnerInfos = response.getBody();

            Map<String, Object> result = new HashMap<>();
            result.put("raceId", request.getRaceId());
            result.put("winner", request.getWinner());
            result.put("status", "FINISHED");
            result.put("winningBets", winnerInfos);

            return ResponseEntity.ok(result);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Unexpected error occurred.");
        }
    }









}
