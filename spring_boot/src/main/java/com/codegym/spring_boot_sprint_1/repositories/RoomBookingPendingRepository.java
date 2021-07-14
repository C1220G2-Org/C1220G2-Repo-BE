package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.RoomBookingPending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomBookingPendingRepository extends JpaRepository<RoomBookingPending, Long> {
    List<RoomBookingPending> findAllByCode(String code);
}
