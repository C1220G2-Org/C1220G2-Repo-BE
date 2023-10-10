package com.codegym.spring_boot_sprint_1.model.dto;

public class ResultDto {
    private Long id;
    private String username;
    private Long quiz;
    private long scores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getQuiz() {
        return quiz;
    }

    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }
}
