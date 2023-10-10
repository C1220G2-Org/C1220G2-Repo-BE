package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quizs")
public class Quiz {

    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer timer;
    private Integer unitScore;
    private Integer targetScore;
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "quiz_category_id")
    private QuizCategory quizcategory;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions = new HashSet<>();
    private boolean enabled;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, targetEntity = Result.class)
    @JsonIgnore
    private Set<Result> result = new HashSet<>();

    public Quiz() {
    }

    public Quiz(String title, String description, Integer timer, Integer unitScore, Integer targetScore, LocalDateTime dateCreated, QuizCategory quizcategory, boolean enabled) {
        this.title = title;
        this.description = description;
        this.timer = timer;
        this.unitScore = unitScore;
        this.targetScore = targetScore;
        this.dateCreated = dateCreated;
        this.quizcategory = quizcategory;
        this.enabled = enabled;
    }

    public Quiz(Long id, String title, boolean enabled, Set<Question> questions) {
        this.id = id;
        this.title = title;
        this.enabled = enabled;
        this.questions = questions;
    }

    public Quiz(String title, boolean enabled, Set<Question> questions) {
        this.title = title;
        this.enabled = enabled;
        this.questions = questions;
    }

    public Quiz(String title, Set<Question> questions) {
        this.title = title;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public QuizCategory getQuizCategory() {
        return quizcategory;
    }

    public void setQuizCategory(QuizCategory quizCategory) {
        this.quizcategory = quizCategory;
    }

    public Quiz(String title, String description, Integer timer, Integer unitScore, Integer targetScore, LocalDateTime dateCreated, QuizCategory quizCategory, Set<Question> questions, boolean enabled) {
        this.title = title;
        this.description = description;
        this.timer = timer;
        this.unitScore = unitScore;
        this.targetScore = targetScore;
        this.dateCreated = dateCreated;
        this.quizcategory = quizCategory;
        this.questions = questions;
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public Integer getUnitScore() {
        return unitScore;
    }

    public void setUnitScore(Integer unitScore) {
        this.unitScore = unitScore;
    }

    public Integer getTargetScore() {
        return targetScore;
    }

    public void setTargetScore(Integer targetScore) {
        this.targetScore = targetScore;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public QuizCategory getQuizcategory() {
        return quizcategory;
    }

    public void setQuizcategory(QuizCategory quizcategory) {
        this.quizcategory = quizcategory;
    }

    public Set<Result> getResult() {
        return result;
    }

    public void setResult(Set<Result> result) {
        this.result = result;
    }
}
