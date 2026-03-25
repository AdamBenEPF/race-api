package com.takima.race.race.services;
import com.takima.race.race.entities.Race;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.entities.Runner;


@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll() {
        return raceRepository.findAll();
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", id)
                )
        );
    }

    public List<Race> getByLocation(String location){
        return raceRepository.findByLocation(location);    
    }

    public Race create(Race race) {
        return raceRepository.save(race);
    }

    public Race updateRace(Long id, Race race) {
        Race existingRace = getById(id);
        existingRace.setName(race.getName());
        existingRace.setDate(race.getDate());
        existingRace.setLocation(race.getLocation());
        existingRace.setMaxParticipants(race.getMaxParticipants());
        return raceRepository.save(existingRace);
    }

}