package com.example.bet.controller;

import com.example.bet.dto.BetPublicDto;
import com.example.bet.dto.WinnerInfo;
import com.example.bet.model.Bet;
import com.example.bet.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bets")
public class BetController {

    @Autowired
    private BetService betService;

    @PostMapping
    public ResponseEntity<?> placeBet(@RequestBody Bet betRequest) {
        try {
            Bet placedBet = betService.placeBet(betRequest);
            return ResponseEntity.ok(placedBet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getBets(@RequestParam Long raceId) {
        return ResponseEntity.ok(betService.getBetsByRace(raceId));
    }


    @GetMapping("/winners")
    public ResponseEntity<?> getWinners(
            @RequestParam Long raceId,
            @RequestParam String winner) {

        List<Bet> allBets = betService.getBetsByRace(raceId);
        List<WinnerInfo> winners = allBets.stream()
                .filter(b -> b.getHorse().equalsIgnoreCase(winner))
                .map(b -> new WinnerInfo(b.getId(), b.getClientName(), b.getAmount(), b.getWin()))
                .toList();

        return ResponseEntity.ok(winners);
    }

}
