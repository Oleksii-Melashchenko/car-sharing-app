package com.clozex.carsharingapp.repository.user;

import com.clozex.carsharingapp.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @EntityGraph(value = "User.roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByEmail(String email);
}
