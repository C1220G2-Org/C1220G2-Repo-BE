package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Notification;
import com.codegym.spring_boot_sprint_1.repositories.INotificationRepository;
import com.codegym.spring_boot_sprint_1.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public void save(String content, String image, Long idUser, String background, Long feedbackId) {
        notificationRepository.saveNotification(content, image, idUser, background, feedbackId);
    }

    @Override
    public List<Notification> findByUserId(Long id) {
        return notificationRepository.findAllByUser(id);
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }
}