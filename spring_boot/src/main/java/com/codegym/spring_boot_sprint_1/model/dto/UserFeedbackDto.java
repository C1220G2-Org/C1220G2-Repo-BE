package com.codegym.spring_boot_sprint_1.model.dto;

public class UserFeedbackDto {
    private Long id;
    private String username;

    public UserFeedbackDto() {
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
}
