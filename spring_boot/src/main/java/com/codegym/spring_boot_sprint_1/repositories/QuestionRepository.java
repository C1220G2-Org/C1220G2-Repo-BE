package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionByTitleIsContaining(String title);
    List<Question> findQuestionByTitleIsContainingAndCategoryId(String title, Long categoryId);
    List<Question> findQuestionByCategoryId(Long categoryId);

}
