package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.PlacesJourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesJourneyRepository extends JpaRepository<PlacesJourney, Long> {

}
