package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.Quiz;
import com.codegym.spring_boot_sprint_1.model.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Page<Result> findResultByQuiz(Quiz quiz, Pageable pageable);
}
