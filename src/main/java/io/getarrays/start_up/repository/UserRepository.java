package io.getarrays.start_up.repository;

import io.getarrays.start_up.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailCode(String emailCode);

    boolean existsByUsername(String username);

}
