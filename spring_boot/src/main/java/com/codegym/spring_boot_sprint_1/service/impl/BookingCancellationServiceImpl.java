package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.BookingCancellation;
import com.codegym.spring_boot_sprint_1.repositories.IBookingCancellationRepository;
import com.codegym.spring_boot_sprint_1.service.IBookingCancellationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingCancellationServiceImpl implements IBookingCancellationService {
    @Autowired
    private IBookingCancellationRepository bookingCancellationRepository;


    @Override
    public void saveBookingCancellation(String cancellationReason, String cancellationTime, Long meetingRoomId, Long userId) {
        this.bookingCancellationRepository.saveBookingCancellation(cancellationReason, cancellationTime, meetingRoomId, userId);
    }

    @Override
    public Long findRoomIdByName(String roomName) {
        return this.bookingCancellationRepository.findRoomIdByName(roomName);
    }

    @Override
    public List<BookingCancellation> findBookingCancellationsByUserId(Long userId) {
        return bookingCancellationRepository.findBookingCancellationsByUserId(userId);
    }

    @Override
    public void deleteBookingCancellationByRoomIdAndUserId(Long roomId, Long userId) {
        this.bookingCancellationRepository.deleteBookingCancellationByRoomIdAndUserId(roomId, userId);
    }


    @Override
    public String findRoomNameById(Long roomId) {
        return this.bookingCancellationRepository.findRoomNameById(roomId);
    }
}
