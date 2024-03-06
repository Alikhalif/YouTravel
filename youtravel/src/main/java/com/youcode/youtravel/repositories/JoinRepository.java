package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.Join;
import com.youcode.youtravel.utils.JoinID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<Join, JoinID> {
    
}
