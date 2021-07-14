package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class MeetingRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer floor;
    private Integer capacity;
    private String color;
    private String status;
    private Integer amountUse;
    private Boolean availability;

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    private Set<PropertyMeetingRoom> ratings;

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RoomBooking> roomBookings;

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ImageOfMeetingRoom> image;

    @OneToMany(mappedBy = "meetingRoom", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Feedback> feedbacks;


    public MeetingRoom() {
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Set<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(Set<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<PropertyMeetingRoom> getRatings() {
        return ratings;
    }

    public void setRatings(Set<PropertyMeetingRoom> ratings) {
        this.ratings = ratings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<ImageOfMeetingRoom> getImage() {
        return image;
    }

    public void setImage(Set<ImageOfMeetingRoom> image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmountUse() {
        return amountUse;
    }

    public void setAmountUse(Integer amountUse) {
        this.amountUse = amountUse;
    }
}
