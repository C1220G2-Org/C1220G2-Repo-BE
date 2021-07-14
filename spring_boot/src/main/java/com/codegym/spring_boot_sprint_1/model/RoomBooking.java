package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room_bookings")
public class RoomBooking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(columnDefinition = "date")
    private String registrationDate;

    @JsonProperty(value = "startDate")
    @Column(columnDefinition = "datetime")
    private String startDate;

    @Column(columnDefinition = "datetime")
    private String endDate;

    @ManyToOne
    @JsonProperty(value = "meetingType")
    @JoinColumn(name = "meeting_type_id", nullable = false)
    private MeetingType meetingType;

    @ManyToOne
    @JoinColumn(name = "meeting_room_id", nullable = false)
    @JsonProperty(value = "meetingRoom")
    private MeetingRoom meetingRoom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public RoomBooking() {
    }

    public RoomBooking(String content, String registrationDate, String startDate, String endDate, MeetingType meetingType, MeetingRoom meetingRoom, User user) {
        this.content = content;
        this.registrationDate = registrationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.meetingType = meetingType;
        this.meetingRoom = meetingRoom;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
