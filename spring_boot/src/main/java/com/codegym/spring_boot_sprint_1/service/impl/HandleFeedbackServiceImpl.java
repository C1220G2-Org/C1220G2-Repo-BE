package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.repositories.IHandleFeedbackRepository;
import com.codegym.spring_boot_sprint_1.service.IHandleFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandleFeedbackServiceImpl implements IHandleFeedbackService {
    @Autowired
    IHandleFeedbackRepository handleFeedbackRepository;

    @Override
    public void save(String content, String image, Long idFeedback) {
        handleFeedbackRepository.save(content, image, idFeedback);
    }
}
