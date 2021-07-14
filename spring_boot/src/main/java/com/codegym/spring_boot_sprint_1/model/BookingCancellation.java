package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "booking_cancellation")
public class BookingCancellation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cancellationTime;

    private String cancellationReason;

    private Long userId;

    @Column(name = "meeting_room_id")
    private Long meetingRoomId;

    public BookingCancellation() {
    }

    public BookingCancellation(String cancellationTime, String cancellationReason, Long userId, Long meetingRoomId) {
        this.cancellationTime = cancellationTime;
        this.cancellationReason = cancellationReason;
        this.userId = userId;
        this.meetingRoomId = meetingRoomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCancellationTime() {
        return cancellationTime;
    }

    public void setCancellationTime(String cancellationTime) {
        this.cancellationTime = cancellationTime;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Long meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }
}
