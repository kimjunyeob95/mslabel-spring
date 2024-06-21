package com.mslabel.mslabel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mslabel.mslabel.model.entity.NoticeGroupData;
import com.mslabel.mslabel.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<NoticeGroupData> home() {
        return this.categoryService.findAll();
    }
}
