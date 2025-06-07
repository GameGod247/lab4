package com.example.bet.service;

import com.example.bet.dto.BetPublicDto;
import com.example.bet.model.Bet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class BetService {

    private final Map<Long, List<Bet>> betsByRace = new HashMap<>();
    private final AtomicLong betIdGenerator = new AtomicLong(1);
    private final RestTemplate restTemplate = new RestTemplate();

    public Bet placeBet(Bet betRequest) {
        Double odds = getOddsForHorse(betRequest.getRaceId(), betRequest.getHorse());
        if (odds == null) throw new IllegalArgumentException("No odds found for this horse.");

        Bet bet = new Bet();
        bet.setId(betIdGenerator.getAndIncrement());
        bet.setRaceId(betRequest.getRaceId());
        bet.setHorse(betRequest.getHorse());
        bet.setAmount(betRequest.getAmount());
        bet.setClientName(betRequest.getClientName());
        bet.setWin(bet.getAmount() * odds); // Потенційний виграш

        betsByRace.computeIfAbsent(bet.getRaceId(), k -> new ArrayList<>()).add(bet);
        return bet;
    }

    public List<Bet> getBetsByRace(Long raceId) {
        return betsByRace.getOrDefault(raceId, Collections.emptyList());
    }

    // Метод, що повертає ставки по гонці без clientName (DTO)
    public List<BetPublicDto> getPublicBetsByRace(Long raceId) {
        return getBetsByRace(raceId).stream()
                .map(b -> new BetPublicDto(
                        b.getId(),
                        b.getRaceId(),
                        b.getHorse(),
                        b.getAmount(),
                        b.getWin()
                ))
                .collect(Collectors.toList());
    }

    private Double getOddsForHorse(Long raceId, String horse) {
        try {
            String url = "http://localhost:8082/odds?raceId=" + raceId;
            Odds[] oddsArray = restTemplate.getForObject(url, Odds[].class);
            if (oddsArray != null) {
                for (Odds o : oddsArray) {
                    if (o.getHorse().equalsIgnoreCase(horse)) {
                        return o.getOdds();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Odds {
        private Long raceId;
        private String horse;
        private double odds;

        public String getHorse() { return horse; }
        public double getOdds() { return odds; }
    }
}
