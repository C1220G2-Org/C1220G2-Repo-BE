package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Quiz;
import com.codegym.spring_boot_sprint_1.model.Result;
import com.codegym.spring_boot_sprint_1.repositories.ResultRepository;
import com.codegym.spring_boot_sprint_1.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService implements IGeneralService<Result> {
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Page<Result> findAll(Pageable pageable) {
        return resultRepository.findAll(pageable);
    }

    @Override
    public Result findById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public void deleteById(Long id) {
        resultRepository.deleteById(id);
    }

    public Page<Result> findByQuizId(Quiz quiz, Pageable pageable) {
        return resultRepository.findResultByQuiz(quiz, pageable);
    }
}
