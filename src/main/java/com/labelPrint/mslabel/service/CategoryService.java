package com.labelPrint.mslabel.service;

import com.labelPrint.mslabel.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import com.labelPrint.mslabel.model.entity.NoticeGroupData;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<NoticeGroupData> findAll() {
        return categoryRepository.findAllByDeletedAtIsNull();
    }

    public Optional<NoticeGroupData> findById(Long id) {
        return categoryRepository.findByIdAndDeletedAtIsNull(id);
    }


}