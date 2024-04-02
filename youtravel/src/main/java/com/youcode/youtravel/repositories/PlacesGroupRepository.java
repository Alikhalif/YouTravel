package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.PlacesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesGroupRepository extends JpaRepository<PlacesGroup, Long> {

}
