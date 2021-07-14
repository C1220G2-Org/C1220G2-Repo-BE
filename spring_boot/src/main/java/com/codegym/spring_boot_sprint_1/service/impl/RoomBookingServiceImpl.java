package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.RoomBooking;
import com.codegym.spring_boot_sprint_1.repositories.IRoomBookingRepository;
import com.codegym.spring_boot_sprint_1.service.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomBookingServiceImpl implements IRoomBookingService {
    @Autowired
    private IRoomBookingRepository roomBookingRepository;

    @Override
    public List<RoomBooking> findAll(Long id) {
        return roomBookingRepository.findAll(id);
    }

    @Override
    public void deleteRoomBookingById(Long id) {
        this.roomBookingRepository.deleteRoomBookingById(id);
    }

    @Override
    public Optional<RoomBooking> findById(Long id) {
        return roomBookingRepository.findById(id);
    }

    @Override
    public RoomBooking save(RoomBooking t) {
        return roomBookingRepository.save(t);
    }

    @Override
    public void deleteById(Long idDelete) {
        roomBookingRepository.deleteById(idDelete);
    }

    @Override
    public List<RoomBooking> findBookingRoomById(Long id) {
        return roomBookingRepository.findBookingRoomById(id);
    }

    @Override
    public void deleteRoomById(Long id) {
        roomBookingRepository.deleteRoomById(id);
    }
}
