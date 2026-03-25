package com.takima.race.race.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;


import java.util.List;


@RestController
@RequestMapping("/races")
public class RaceController {
     private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getRaces(@RequestParam(required = false) String location) {
        if (location != null && !location.isBlank()) {
            return raceService.getByLocation(location);
        }
        return raceService.getAll();
    }

     @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }



    @PostMapping
    public Race create(
        @RequestBody Race race) {
        return raceService.create(race);
    }

    @PutMapping("/{id}")
    public Race update(
        @PathVariable Long id,
        @RequestBody Race race) {
            return raceService.updateRace(id, race);
    }


}