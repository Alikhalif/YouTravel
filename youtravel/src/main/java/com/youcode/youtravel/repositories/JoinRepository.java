package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<Join, Long> {
}
