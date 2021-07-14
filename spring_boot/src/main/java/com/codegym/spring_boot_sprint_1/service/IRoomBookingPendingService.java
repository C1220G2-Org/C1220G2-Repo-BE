package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.RoomBookingPending;

import java.util.List;
import java.util.Optional;

public interface IRoomBookingPendingService {

    List<RoomBookingPending> findAll();

    Optional<RoomBookingPending> findById(Long id);

    RoomBookingPending save(RoomBookingPending t);

    void deleteById(Long idDelete);

    List<RoomBookingPending> findAllByCode(String code);

}
