package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    @Query("SELECT j FROM Journey j WHERE j.countryStarting = :countryStarting " +
            "AND j.stateStarting = :stateStarting " +
            "AND j.cityStarting = :cityStarting " +
            "AND j.countryArrival = :countryArrival " +
            "AND j.stateArrival = :stateArrival " +
            "AND j.cityArrival = :cityArrival " +
            "AND j.startDate >= :startDate " +
            "AND j.endDate = :endDate")
    List<Journey> findJourneysByCriteria(@Param("countryStarting") String countryStarting,
                                         @Param("stateStarting") String stateStarting,
                                         @Param("cityStarting") String cityStarting,
                                         @Param("countryArrival") String countryArrival,
                                         @Param("stateArrival") String stateArrival,
                                         @Param("cityArrival") String cityArrival,
                                         @Param("startDate") LocalDateTime startDate,
                                         @Param("endDate") LocalDateTime endDate);
}
