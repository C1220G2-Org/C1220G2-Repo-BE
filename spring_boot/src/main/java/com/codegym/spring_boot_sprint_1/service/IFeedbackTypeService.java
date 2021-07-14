package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.FeedbackType;

import java.util.List;

public interface IFeedbackTypeService {
    List<FeedbackType> findAll();

    FeedbackType findById(Long id);
}
