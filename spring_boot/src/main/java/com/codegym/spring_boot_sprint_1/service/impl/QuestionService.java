package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Option;
import com.codegym.spring_boot_sprint_1.model.Question;
import com.codegym.spring_boot_sprint_1.repositories.CategoryRepository;
import com.codegym.spring_boot_sprint_1.repositories.OptionRepository;
import com.codegym.spring_boot_sprint_1.repositories.QuestionRepository;
import com.codegym.spring_boot_sprint_1.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService implements IGeneralService<Question> {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> searchByKeyword(String keyword) {
        return questionRepository.findQuestionByTitleIsContaining(keyword);
    }

    public List<Question> searchByKeywordAndCategoryId(String keyword, Long categoryId) {
        return questionRepository.findQuestionByTitleIsContainingAndCategoryId(keyword, categoryId);
    }

    public List<Question> searchQuestionByCategoryId(Long categoryId) {
        return questionRepository.findQuestionByCategoryId(categoryId);
    }

}
