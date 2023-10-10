package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    private LocalDateTime dateSubmit;
    private long scores;

    public Result() {
    }

    public Result(String username, Quiz quiz, LocalDateTime dateSubmit, long scores) {
        this.username = username;
        this.quiz = quiz;
        this.dateSubmit = dateSubmit;
        this.scores = scores;
    }

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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public LocalDateTime getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(LocalDateTime dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long grade) {
        this.scores = grade;
    }
}
