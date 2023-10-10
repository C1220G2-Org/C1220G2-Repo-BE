package com.codegym.spring_boot_sprint_1.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuizDto {
    private Long id;
    private String title;
    private String description;
    private Integer timer;
    private Integer unitScore;
    private Integer targetScore;
    private LocalDateTime dateCreated;
    private Long quizCategory;
    private List<Integer> questionIdList;
    private boolean enabled;

    public QuizDto() {
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

    public Long getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(Long quizCategory) {
        this.quizCategory = quizCategory;
    }

    public List<Integer> getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(List<Integer> questionIdList) {
        this.questionIdList = questionIdList;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
