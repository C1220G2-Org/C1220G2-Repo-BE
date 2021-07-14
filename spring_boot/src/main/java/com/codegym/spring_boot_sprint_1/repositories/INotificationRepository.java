package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into notification(content, image, user_id, background, feedback_id) " +
            "values " +
            "(?1,?2,?3,?4,?5) ", nativeQuery = true)
    void saveNotification(String content, String image, Long idUser, String background, Long feedbackId);

    @Query(value = "select * from notification " +
            "where user_id = ?1 " +
            "order by notification_id DESC ", nativeQuery = true)
    List<Notification> findAllByUser(Long id);
}