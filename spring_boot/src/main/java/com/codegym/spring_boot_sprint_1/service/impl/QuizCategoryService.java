package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.QuizCategory;
import com.codegym.spring_boot_sprint_1.repositories.QuizCategoryRepository;
import com.codegym.spring_boot_sprint_1.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizCategoryService implements IGeneralService<QuizCategory> {
    @Autowired
    private QuizCategoryRepository quizCategoryRepository;

    @Override
    public List<QuizCategory> findAll() {
        return quizCategoryRepository.findAll();
    }

    @Override
    public Page<QuizCategory> findAll(Pageable pageable) {
        return quizCategoryRepository.findAll(pageable);
    }

    @Override
    public QuizCategory findById(Long id) {
        return quizCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public QuizCategory save(QuizCategory category) {
        return quizCategoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        quizCategoryRepository.deleteById(id);
    }

    public Page<QuizCategory> searchByName(String name, Pageable pageable) {
        return quizCategoryRepository.findQuizCategoriesByName(name, pageable);
    }

}
