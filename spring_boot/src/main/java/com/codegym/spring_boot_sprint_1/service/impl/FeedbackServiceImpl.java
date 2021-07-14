package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Feedback;
import com.codegym.spring_boot_sprint_1.repositories.IFeedbackRepository;
import com.codegym.spring_boot_sprint_1.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    @Autowired
    IFeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAllFeedback();
    }

    @Override
    public Optional<Feedback> findFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public void save(String title, String date, String content, String[] image, boolean status, Long feedbackType, Long meetingRoom,
                     Long typeError, Long user) {
        feedbackRepository.save(title, date, content, image, status, feedbackType, meetingRoom, typeError, user);
    }

    @Override
    public void saveTechnicalFeedback(String title, String date, String content, String image, boolean status, Long feedbackType, Long meetingRoom, Long typeError, Long user) {
        feedbackRepository.saveTechnicalFeedback(title, date, content, image, status, feedbackType, meetingRoom, typeError, user);
    }

    @Override
    public List<Feedback> search(String keyWord, boolean status) {
        return feedbackRepository.search(keyWord, status);
    }

    @Override
    public List<Feedback> searchNotStatus(String keyWord) {
        return feedbackRepository.searchNotStatus(keyWord);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<Feedback> searchUser(Long userId) {
        return feedbackRepository.searchUser(userId);
    }

    @Override
    public void save(String title, String date, String content, String image, boolean status, Long feedbackType, Long typeError, Long user) {
        feedbackRepository.save(title, date, content, image, status, feedbackType, typeError, user);
    }
}