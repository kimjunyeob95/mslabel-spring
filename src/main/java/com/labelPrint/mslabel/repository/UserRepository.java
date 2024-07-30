package com.labelPrint.mslabel.repository;

import java.util.Optional;

import com.labelPrint.mslabel.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
