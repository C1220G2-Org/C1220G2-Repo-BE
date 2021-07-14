package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.FeedbackType;
import com.codegym.spring_boot_sprint_1.repositories.IFeedbackTypeRepository;
import com.codegym.spring_boot_sprint_1.service.IFeedbackTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackTypeServiceImpl implements IFeedbackTypeService {
    @Autowired
    IFeedbackTypeRepository feedbackTypeRepository;

    @Override
    public List<FeedbackType> findAll() {
        return feedbackTypeRepository.findAll();
    }

    @Override
    public FeedbackType findById(Long id) {
        return feedbackTypeRepository.findById(id).orElse(null);
    }
}
