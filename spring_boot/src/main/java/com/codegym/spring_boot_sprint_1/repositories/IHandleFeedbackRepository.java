package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.HandleFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IHandleFeedbackRepository extends JpaRepository<HandleFeedback, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into handle_feedback(handle_feedback_content,image, id_feedback) " +
            "values " +
            "(?1,?2,?3) ", nativeQuery = true)
    void save(String content, String image, Long idFeedback);
}
