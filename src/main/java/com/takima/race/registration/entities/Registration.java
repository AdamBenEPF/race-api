package com.takima.race.registration.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
    @JoinColumn(name = "runner_id", nullable = false)
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race race;

    private LocalDate registrationDate = LocalDate.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // même instance
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        // Deux inscriptions sont égales si elles ont le même runner et la même race
        return Objects.equals(runner, that.runner) &&
               Objects.equals(race, that.race);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runner, race);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    
}