package com.labelPrint.mslabel.repository;

import com.labelPrint.mslabel.model.entity.AdminData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDataRepository extends JpaRepository<AdminData, Long> {
    public Optional<AdminData> findByUserId(String userId);
    public Optional<AdminData> findByEmail(String email);
}
