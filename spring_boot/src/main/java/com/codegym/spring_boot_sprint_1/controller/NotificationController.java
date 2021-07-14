package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.Notification;
import com.codegym.spring_boot_sprint_1.model.dto.NotificationDisplayDto;
import com.codegym.spring_boot_sprint_1.model.dto.NotificationDto;
import com.codegym.spring_boot_sprint_1.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;
    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/list/{id}")
    public ResponseEntity<List<NotificationDto>> getListMeetingRoom(@PathVariable Long id) {
        List<Notification> notificationList = notificationService.findByUserId(id);
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        if (notificationList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Notification notification : notificationList) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setId(notification.getId());
            notificationDto.setContent(notification.getContent());
            notificationDto.setBackground(notification.getBackground());
            notificationDto.setFeedbackId(notification.getFeedbackId());
            notificationDtoList.add(notificationDto);
        }
        return new ResponseEntity<>(notificationDtoList, HttpStatus.OK);
    }

    @PutMapping("/patchBackground")
    public ResponseEntity<Notification> updateBackground(@RequestBody NotificationDto notificationDto) {
        System.out.println(notificationDto.getId());
        Optional<Notification> notificationOptional = notificationService.findById(notificationDto.getId());
        if (!notificationOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Notification notification = notificationOptional.get();
            notification.setBackground("none");
            notificationService.save(notification);
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }
    }

    @GetMapping("/notify")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String getNotification(@RequestParam Long[] userId) {
        NotificationDisplayDto notificationDisplayDto = new NotificationDisplayDto();
        notificationDisplayDto.setUserId(userId);
        // Push notifications to front-end
        template.convertAndSend("/topic/notification", notificationDisplayDto);
        return "Notifications successfully sent to Angular !";
    }
}