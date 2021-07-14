package com.codegym.spring_boot_sprint_1.model.dto;

public class BookingCancellationDto {
    private String cancellationReason;
    private String cancellationTime;
    private Long userId;
    private String roomName;
    private Long bookingId;

    public BookingCancellationDto() {
    }

    public BookingCancellationDto(String cancellationReason, String cancellationTime, Long userId, String roomName, Long bookingId) {
        this.cancellationReason = cancellationReason;
        this.cancellationTime = cancellationTime;
        this.userId = userId;
        this.roomName = roomName;
        this.bookingId = bookingId;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getCancellationTime() {
        return cancellationTime;
    }

    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
