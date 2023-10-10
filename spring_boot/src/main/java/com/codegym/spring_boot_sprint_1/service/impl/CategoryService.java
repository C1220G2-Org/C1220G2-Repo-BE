package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Category;
import com.codegym.spring_boot_sprint_1.repositories.CategoryRepository;
import com.codegym.spring_boot_sprint_1.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IGeneralService<Category> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Page<Category> searchByName(String name, Pageable pageable) {
        return categoryRepository.findCategoryByName(name, pageable);
    }

}
