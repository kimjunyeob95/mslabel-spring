package com.mslabel.mslabel.service;

import org.springframework.stereotype.Service;

import com.mslabel.mslabel.model.entity.NoticeGroupData;
import com.mslabel.mslabel.repository.CategoryRepository;
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