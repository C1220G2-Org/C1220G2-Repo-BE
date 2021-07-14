package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.RoomBooking;

import java.util.List;
import java.util.Optional;

public interface IRoomBookingService {
    List<RoomBooking> findAll(Long id);

    void deleteRoomBookingById(Long id);

    Optional<RoomBooking> findById(Long id);

    RoomBooking save(RoomBooking t);

    void deleteById(Long idDelete);

    List<RoomBooking> findBookingRoomById(Long id);

    void deleteRoomById(Long id);
}
