package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.Group;
import com.youcode.youtravel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByUser(User user);
}
