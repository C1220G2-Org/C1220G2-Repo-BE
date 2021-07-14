package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    List<Feedback> findAllFeedback();

    Optional<Feedback> findFeedbackById(Long id);

    void save(String title, String date, String content, String[] image, boolean status, Long feedbackType, Long meetingRoom,
              Long typeError, Long user);

    void saveTechnicalFeedback(String title, String date, String content, String image, boolean status, Long feedbackType, Long meetingRoom,
                               Long typeError, Long user);

    List<Feedback> search(String keyWord, boolean status);

    List<Feedback> searchNotStatus(String keyWord);

    void deleteFeedback(Long id);

    List<Feedback> searchUser(Long userId);

    void save(String title, String date, String content, String image, boolean status, Long feedbackType, Long typeError, Long user);
}