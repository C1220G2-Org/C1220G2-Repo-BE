package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Question;
import com.codegym.spring_boot_sprint_1.model.Quiz;
import com.codegym.spring_boot_sprint_1.repositories.QuizRepository;
import com.codegym.spring_boot_sprint_1.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService implements IGeneralService<Quiz> {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Page<Quiz> findAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    @Override
    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }

    public Page<Quiz> searchByKeyword(String keyword, Pageable pageable) {
        return quizRepository.findQuizByTitle(keyword, pageable);
    }
}
