package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_categories")
public class QuizCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_category_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "quizcategory", cascade = CascadeType.ALL, targetEntity =Quiz.class)
    @JsonIgnore
    private List<Quiz> quizzes;


    public QuizCategory() {
    }

    public QuizCategory(String name, List<Quiz> quizzes) {
        this.name = name;
        this.quizzes = quizzes;
    }

    public QuizCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public void setQuestions(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
