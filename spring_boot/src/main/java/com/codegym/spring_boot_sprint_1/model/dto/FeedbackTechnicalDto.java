package com.codegym.spring_boot_sprint_1.model.dto;

import java.util.Arrays;

public class FeedbackTechnicalDto {
    private Long id;
    private String feedbackTitle;
    private String feedbackContent;
    private String[] image;
    private Long typeError;
    private String feedbackTime;
    private boolean status;
    private Long userFeedback;
    private Long feedbackType;

    public FeedbackTechnicalDto(Long id, String feedbackTitle, String feedbackContent, String[] image, Long typeError, String feedbackTime, boolean status, Long userFeedback, Long feedbackType) {
        this.id = id;
        this.feedbackTitle = feedbackTitle;
        this.feedbackContent = feedbackContent;
        this.image = image;
        this.typeError = typeError;
        this.feedbackTime = feedbackTime;
        this.status = status;
        this.userFeedback = userFeedback;
        this.feedbackType = feedbackType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public Long getTypeError() {
        return typeError;
    }

    public void setTypeError(Long typeError) {
        this.typeError = typeError;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(Long userFeedback) {
        this.userFeedback = userFeedback;
    }

    public Long getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Long feedbackType) {
        this.feedbackType = feedbackType;
    }
}