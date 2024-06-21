package com.mslabel.mslabel.repository;

import com.mslabel.mslabel.model.entity.Tokens;

import java.util.List;

public interface TokenRepositoryCustom {
    List<Tokens> findAllValidTokenByUserName(String userName);
}