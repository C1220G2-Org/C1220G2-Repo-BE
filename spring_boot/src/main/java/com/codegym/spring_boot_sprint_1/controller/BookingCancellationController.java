package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.BookingCancellation;
import com.codegym.spring_boot_sprint_1.model.dto.BookingCancellationDto;
import com.codegym.spring_boot_sprint_1.service.IBookingCancellationService;
import com.codegym.spring_boot_sprint_1.service.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class BookingCancellationController {
    @Autowired
    private IBookingCancellationService bookingCancellationService;

    @Autowired
    private IRoomBookingService roomBookingService;

    @PostMapping("/booking-cancellation")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BookingCancellationDto bookingCancellationDto) {
        Long roomId = this.bookingCancellationService.findRoomIdByName(bookingCancellationDto.getRoomName());

        this.bookingCancellationService
                .saveBookingCancellation(bookingCancellationDto.getCancellationReason(),
                        bookingCancellationDto.getCancellationTime(),
                        roomId,
                        bookingCancellationDto.getUserId());

        this.roomBookingService.deleteRoomBookingById(bookingCancellationDto.getBookingId());
    }

    @GetMapping("/booking-cancellation/{userId}")
    public ResponseEntity<List<BookingCancellationDto>> findBookingCancellationsOfUser(@PathVariable Long userId) {
        List<BookingCancellation> bookingCancellationList = this.bookingCancellationService.findBookingCancellationsByUserId(userId);
        List<BookingCancellationDto> result = new ArrayList<>();
        for (BookingCancellation bookingCancellation : bookingCancellationList) {
            BookingCancellationDto bookingCancellationDto = new BookingCancellationDto();

            bookingCancellationDto.setCancellationReason(bookingCancellation.getCancellationReason());
            bookingCancellationDto.setCancellationTime(bookingCancellation.getCancellationTime());
            bookingCancellationDto.setUserId(bookingCancellation.getUserId());

            String roomName = this.bookingCancellationService.findRoomNameById(bookingCancellation.getMeetingRoomId());
            bookingCancellationDto.setRoomName(roomName);

            bookingCancellationDto.setBookingId((0L));

            result.add(bookingCancellationDto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/booking-cancellation/delete/{roomId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookingCancellationById(@PathVariable Long roomId, @PathVariable Long userId) {
        this.bookingCancellationService.deleteBookingCancellationByRoomIdAndUserId(roomId, userId);
    }
}
