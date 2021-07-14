package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "meeting_types")
public class MeetingType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meeting_type_name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "meetingType", cascade = CascadeType.ALL)
    private List<RoomBooking> roomBookings;

    public MeetingType() {
    }

    public MeetingType(String name, List<RoomBooking> roomBookings) {
        this.name = name;
        this.roomBookings = roomBookings;
    }

    public MeetingType(String name) {
        this.name = name;
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

    public List<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(List<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
    }
}
