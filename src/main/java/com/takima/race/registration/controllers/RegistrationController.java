package com.takima.race.registration.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.takima.race.registration.services.RegistrationService;
import java.util.Map;


import com.takima.race.runner.entities.Runner;
import java.util.List;

@RestController
@RequestMapping("/races")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/{raceId}/registrations")
    public void registerRunnerToRace(@RequestBody Map<String, Long> body, @PathVariable Long raceId) {
        Long runnerId = body.get("runnerId");
        registrationService.registerRunnerToRace(runnerId, raceId);
    }

    @GetMapping("/{raceId}/registrations")
    public List<Runner> getRaceParticipants(@PathVariable Long raceId) {
        return registrationService.getRaceParticipants(raceId);
    }

    @GetMapping("/{raceId}/participants/count")
    public long countRaceParticipants(@PathVariable Long raceId) {
        return registrationService.countRaceParticipants(raceId);
    }

    
}