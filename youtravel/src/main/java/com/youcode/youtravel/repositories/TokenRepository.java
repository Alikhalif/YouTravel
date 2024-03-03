package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
        select t from Token t inner join User u on t.user.uid = u.uid
        where t.user.uid = :userId and t.loggedOut = false
    """)
    List<Token> findAllTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
