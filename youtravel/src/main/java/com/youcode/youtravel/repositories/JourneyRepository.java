package com.youcode.youtravel.repositories;

import com.youcode.youtravel.dto.JourneySearchDTO;
import com.youcode.youtravel.dto.ResponseDto.JourneyDTOResp;
import com.youcode.youtravel.entities.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Long> {
    @Query("SELECT j FROM Journey j WHERE j.countryStarting = :#{#criteria.countryStarting} " +
            "OR j.stateStarting = :#{#criteria.stateStarting} " +
            "OR j.cityStarting = :#{#criteria.cityStarting} " +
            "OR j.countryArrival = :#{#criteria.countryArrival} " +
            "OR j.stateArrival = :#{#criteria.stateArrival} " +
            "OR j.cityArrival = :#{#criteria.cityArrival} " +
            "OR j.startDate >= :#{#criteria.startDate} " +
            "OR j.endDate = :#{#criteria.endDate}")
    List<Journey> findJourneysByCriteria(@Param("criteria") JourneySearchDTO criteria);


    @Query("SELECT j FROM Journey j WHERE j.startDate >= :currentDate")
    List<Journey> findAllJourneysByCurrentDate(LocalDateTime currentDate);

    @Query("SELECT j FROM Journey j WHERE j.user.uid = :userId")
    List<Journey> findJourneysByUserId(@Param("userId") Long userId);
}
