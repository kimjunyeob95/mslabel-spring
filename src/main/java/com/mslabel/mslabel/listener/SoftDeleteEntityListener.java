package com.mslabel.mslabel.listener;

import java.sql.Timestamp;

import com.mslabel.mslabel.model.entity.SoftDeleteEntity;

import jakarta.persistence.PreRemove;

public class SoftDeleteEntityListener {

    @PreRemove
    public void preRemove(SoftDeleteEntity entity) {
        entity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
    }
}