package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.Notification;

import java.util.List;
import java.util.Optional;

public interface INotificationService {
    void save(String content, String image, Long idUser, String background, Long feedbackId);

    List<Notification> findByUserId(Long id);

    Optional<Notification> findById(Long id);

    void save(Notification notification);
}