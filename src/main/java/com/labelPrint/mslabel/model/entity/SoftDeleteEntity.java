package com.labelPrint.mslabel.model.entity;

import java.sql.Timestamp;

import com.labelPrint.mslabel.listener.SoftDeleteEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreRemove;

@MappedSuperclass
@EntityListeners(SoftDeleteEntityListener.class)
public abstract class SoftDeleteEntity {

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PreRemove
    public void preRemove() {
        this.deletedAt = new Timestamp(System.currentTimeMillis());
    }
}