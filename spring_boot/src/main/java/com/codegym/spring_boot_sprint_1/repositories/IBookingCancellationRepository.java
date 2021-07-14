package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.BookingCancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IBookingCancellationRepository extends JpaRepository<BookingCancellation, Long> {
    @Transactional
    @Modifying
    @Query(value = "insert into booking_cancellation(cancellation_reason, cancellation_time, meeting_room_id, user_id)\n" +
            "value(?1, ?2, ?3, ?4);", nativeQuery = true)
    void saveBookingCancellation(String cancellationReason, String cancellationTime, Long meetingRoomId, Long userId);

    @Query(value = "select id from meeting_room\n" +
            "where `name` = :roomName", nativeQuery = true)
    Long findRoomIdByName(@Param("roomName") String roomName);

    @Query(value = "select `name` from meeting_room\n" +
            "where id = :roomId", nativeQuery = true)
    String findRoomNameById(Long roomId);

    @Query(value = "select * from booking_cancellation\n" +
            "where user_id = ?1", nativeQuery = true)
    List<BookingCancellation> findBookingCancellationsByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from booking_cancellation\n" +
            "where meeting_room_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteBookingCancellationByRoomIdAndUserId(Long roomId, Long userId);
}
