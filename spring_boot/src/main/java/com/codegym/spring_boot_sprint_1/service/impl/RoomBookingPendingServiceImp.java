package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.RoomBookingPending;
import com.codegym.spring_boot_sprint_1.repositories.RoomBookingPendingRepository;
import com.codegym.spring_boot_sprint_1.service.IRoomBookingPendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomBookingPendingServiceImp implements IRoomBookingPendingService {

    @Autowired
    private RoomBookingPendingRepository roomBookingPendingRepository;


    @Override
    public List<RoomBookingPending> findAll() {
        return roomBookingPendingRepository.findAll();
    }

    @Override
    public Optional<RoomBookingPending> findById(Long id) {
        return roomBookingPendingRepository.findById(id);
    }

    @Override
    public RoomBookingPending save(RoomBookingPending t) {
        return roomBookingPendingRepository.save(t);
    }

    @Override
    public void deleteById(Long idDelete) {
        roomBookingPendingRepository.deleteById(idDelete);
    }

    @Override
    public List<RoomBookingPending> findAllByCode(String code) {
        return roomBookingPendingRepository.findAllByCode(code);
    }
}
