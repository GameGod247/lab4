package com.example.RaceService.service;

import com.example.RaceService.model.Race;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RaceService {
    private final Map<Long, Race> races = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Race createRace(Race race) {
        race.setId(idGenerator.getAndIncrement());
        race.setStatus("PENDING");
        races.put(race.getId(), race);
        return race;
    }

    public Race getRace(Long id) {
        return races.get(id);
    }

    public List<Race> getAllRaces() {
        return new ArrayList<>(races.values());
    }

    public Race updateRace(Long id, Race updated) {
        Race existing = races.get(id);
        if (existing == null) return null;
        existing.setName(updated.getName());
        existing.setDate(updated.getDate());
        existing.setHorses(updated.getHorses());
        return existing;
    }

    public void deleteRace(Long id) {
        races.remove(id);
    }

    public Race setResult(Long raceId, String winner) {
        Race race = races.get(raceId);
        if (race == null) throw new IllegalStateException("Race not found");
        if (!race.getHorses().contains(winner)) throw new IllegalStateException("Winner not in race");
        race.setStatus("FINISHED");
        race.setWinner(winner);
        return race;
    }
}
