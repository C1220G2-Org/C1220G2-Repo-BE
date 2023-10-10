package com.codegym.spring_boot_sprint_1.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    private Long id;
    private String title;
    private Integer correctOption;
    private int type;
    private LocalDateTime dateCreated;
    private boolean enabled;
    private Long category;
    private List<OptionDto> options;

    public QuestionDto() {
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

    public Integer getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Integer correctOption) {
        this.correctOption = correctOption;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
