package com.takima.race.runner.controllers;

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.takima.race.registration.services.RegistrationService;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;
    private final RegistrationService registrationService;

    public RunnerController(RunnerService runnerService, RegistrationService registrationService) {
        this.runnerService = runnerService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }

    @PostMapping
    public Runner create(
        @Valid @RequestBody Runner runner) {
        return runnerService.create(runner);
    }

    @PutMapping("/{id}")
    public Runner update(
        @PathVariable Long id,
        @RequestBody Runner runner) {
            return runnerService.updateRunner(id, runner);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        runnerService.deleteRunner(id);
    }

    @GetMapping(path = "/{runnerId}/races")
    public List<Race> getRunnerRaces(@PathVariable Long runnerId) {
        return registrationService.getRunnerRaces(runnerId);
    }
}