package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IRoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    @Query(value = "select * from room_bookings where room_bookings.meeting_room_id = ?1 ", nativeQuery = true)
    List<RoomBooking> findAll(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from room_bookings\n" +
            "where id = ?1", nativeQuery = true)
    void deleteRoomBookingById(Long id);

    @Query(value = "select * from room_bookings where meeting_room_id = ?1 ; ", nativeQuery = true)
    List<RoomBooking> findBookingRoomById(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM room_bookings " +
            "WHERE meeting_room_id = ?1 ", nativeQuery = true)
    void deleteRoomById(Long roomId);
}
