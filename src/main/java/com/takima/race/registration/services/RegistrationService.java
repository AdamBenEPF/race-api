package com.takima.race.registration.services;

import org.springframework.stereotype.Service;
import com.takima.race.runner.repositories.RunnerRepository;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.registration.entities.Registration;
import com.takima.race.runner.entities.Runner;
import com.takima.race.race.entities.Race;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RaceRepository raceRepository;
    private final RunnerRepository runnerRepository;

    public RegistrationService(RegistrationRepository registrationRepository, RaceRepository raceRepository, RunnerRepository runnerRepository) {
        this.registrationRepository = registrationRepository;
        this.raceRepository = raceRepository;
        this.runnerRepository = runnerRepository;
    }

    public Registration registerRunnerToRace(Long runnerId, Long raceId) {
        Runner runner = runnerRepository.findById(runnerId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", runnerId)
                )
);
        Race race = raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", raceId)
                )
        );
        if (registrationRepository.existsByRaceAndRunner(race, runner)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Runner %s is already registered for Race %s", runnerId, raceId)
            );
        }
        if (registrationRepository.countByRaceId(raceId)>= race.getMaxParticipants()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Race %s is full", raceId)
            );
        }

        Registration registration = new Registration();
        registration.setRunner(runner);
        registration.setRace(race);
        return registrationRepository.save(registration);
    }


    public List<Runner> getRaceParticipants(Long raceId) {
        Race race = raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", raceId)
                )
        );
        return registrationRepository.findByRace(race).stream()
                .map(Registration::getRunner)
                .toList();
    }

    public long countRaceParticipants(Long raceId) {
        if(!raceRepository.existsById(raceId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Race %s not found", raceId)
            );
        }

        return registrationRepository.countByRaceId(raceId);
    }

    public List<Race> getRunnerRaces(Long runnerId) {
        if(!runnerRepository.existsById(runnerId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Runner %s not found", runnerId)
            );
        }        
        return registrationRepository.findRacesByRunnerId(runnerId);
    }

}