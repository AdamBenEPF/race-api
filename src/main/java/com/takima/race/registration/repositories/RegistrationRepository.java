package com.takima.race.registration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.takima.race.registration.entities.Registration;
import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByRaceAndRunner(Race race, Runner runner);
    List<Registration> findByRace(Race race);
    long countByRaceId(Long raceId);

    @Query("SELECT r.race FROM Registration r WHERE r.runner.id = :runnerId")
    List<Race> findRacesByRunnerId(Long runnerId);

}