package com.mslabel.mslabel.repository;

import com.mslabel.mslabel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
