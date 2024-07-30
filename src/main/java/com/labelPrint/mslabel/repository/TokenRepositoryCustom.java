package com.labelPrint.mslabel.repository;

import com.labelPrint.mslabel.model.entity.Tokens;

import java.util.List;

public interface TokenRepositoryCustom {
    List<Tokens> findAllValidTokenByUserName(String userName);
}