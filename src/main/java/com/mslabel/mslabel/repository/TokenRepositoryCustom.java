package com.mslabel.mslabel.repository;

import com.mslabel.mslabel.model.Tokens;

import java.util.List;

public interface TokenRepositoryCustom {
    List<Tokens> findAllValidTokenByUserName(String userName);
}