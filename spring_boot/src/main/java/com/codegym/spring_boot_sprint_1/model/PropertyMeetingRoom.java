package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PropertyMeetingRoom implements Serializable {
    @EmbeddedId
    CourseRatingKey id;

    @ManyToOne
    @MapsId("meetingRoomId")
    @JoinColumn(name = "meeting_room_id")
    @JsonIgnore
    MeetingRoom meetingRoom;

    @ManyToOne
    @MapsId("propertyId")
    @JoinColumn(name = "property_id")
    Property property;

    int amountInRoom;

    public PropertyMeetingRoom() {
    }

    public CourseRatingKey getId() {
        return id;
    }

    public void setId(CourseRatingKey id) {
        this.id = id;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getAmountInRoom() {
        return amountInRoom;
    }

    public void setAmountInRoom(int amountInRoom) {
        this.amountInRoom = amountInRoom;
    }
}
