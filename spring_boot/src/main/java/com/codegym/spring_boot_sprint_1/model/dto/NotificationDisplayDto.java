package com.codegym.spring_boot_sprint_1.model.dto;

public class NotificationDisplayDto {
    private Long[] userId;

    public NotificationDisplayDto() {
    }

    public Long[] getUserId() {
        return userId;
    }

    public void setUserId(Long[] userId) {
        this.userId = userId;
    }
}