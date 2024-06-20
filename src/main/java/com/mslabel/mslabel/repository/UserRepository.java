package com.mslabel.mslabel.repository;

import java.util.Optional;
import com.mslabel.mslabel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
