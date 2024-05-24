package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUid(Long uid);
    List<User> findByRole(Role role);


}
