package com.codegym.spring_boot_sprint_1.service;


public interface IHandleFeedbackService {
    void save(String content, String image, Long idFeedback);
}
