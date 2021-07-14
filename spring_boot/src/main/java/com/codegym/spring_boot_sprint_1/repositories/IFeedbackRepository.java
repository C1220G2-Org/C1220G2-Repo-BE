package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query(value = "SELECT * FROM feedback ", nativeQuery = true)
    List<Feedback> findAllFeedback();

    @Query(value = "SELECT * FROM feedback " +
            "WHERE id_feedback = ?1 ", nativeQuery = true)
    Optional<Feedback> findById(Long id);

    @Transactional
    @Modifying
    @Query(value = "insert into feedback(feedback_title, feedback_time,feedback_content, image, `status`, feedback_type_id, meeting_room_id, id_type_error, user_id) " +
            "values " +
            "(?1,?2,?3,?4,?5,?6,?7,?8,?9) ", nativeQuery = true)
    void save(String title, String date, String content, String[] image, boolean status, Long feedbackType, Long meetingRoom,
              Long typeError, Long user);

    @Transactional
    @Modifying
    @Query(value = "insert into feedback(feedback_title, feedback_time,feedback_content, image, `status`, feedback_type_id, meeting_room_id, id_type_error, user_id) " +
            "values " +
            "(?1,?2,?3,?4,?5,?6,?7,?8,?9) ", nativeQuery = true)
    void saveTechnicalFeedback(String title, String date, String content, String image, boolean status, Long feedbackType, Long meetingRoom,
                               Long typeError, Long user);

    @Query(value = "select * from feedback " +
            "left join users on users.user_id = feedback.user_id " +
            "left join meeting_room on meeting_room.id = feedback.meeting_room_id " +
            "where (users.username like %?1% " +
            "or meeting_room.name like %?1%) " +
            "and feedback.status = ?2 ", nativeQuery = true)
    List<Feedback> search(String keyWord, boolean status);

    @Query(value = "select * from feedback " +
            "left join users on users.user_id = feedback.user_id " +
            "left join meeting_room on meeting_room.id = feedback.meeting_room_id " +
            "where users.username like concat('%',?1,'%') " +
            "or meeting_room.name like concat('%',?1,'%') ", nativeQuery = true)
    List<Feedback> searchNotStatus(String keyWord);

    @Query(value = " select * from  feedback  where user_id = ?1 ", nativeQuery = true)
    List<Feedback> searchUser(Long userId);

    @Transactional
    @Modifying
    @Query(value = "insert into feedback(feedback_title, feedback_time,feedback_content, image, `status`, feedback_type_id, id_type_error, user_id) " +
            "values (?1,?2,?3,?4,?5,?6,?7,?8) ", nativeQuery = true)
    void save(String title, String date, String content, String image, boolean status, Long feedbackType, Long typeError, Long user);
}