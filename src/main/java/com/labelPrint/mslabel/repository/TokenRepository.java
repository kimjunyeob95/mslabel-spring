package com.labelPrint.mslabel.repository;

import com.labelPrint.mslabel.model.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Tokens, Long>, TokenRepositoryCustom {
    Optional<Tokens> findByToken(String token);

}