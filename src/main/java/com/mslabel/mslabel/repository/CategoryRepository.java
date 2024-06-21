package com.mslabel.mslabel.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mslabel.mslabel.model.entity.NoticeGroupData;


@Repository
public interface CategoryRepository extends SoftDeleteRepository<NoticeGroupData, Long> {
    public Optional<NoticeGroupData> findById(Long id);
}
