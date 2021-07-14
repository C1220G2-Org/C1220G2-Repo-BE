package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.BookingCancellation;

import java.util.List;

public interface IBookingCancellationService {
    void saveBookingCancellation(String cancellationReason, String cancellationTime, Long meetingRoomId, Long userId);

    Long findRoomIdByName(String roomName);

    List<BookingCancellation> findBookingCancellationsByUserId(Long userId);

    void deleteBookingCancellationByRoomIdAndUserId(Long roomId, Long userId);

    String findRoomNameById(Long roomId);
}
