package com.codegym.spring_boot_sprint_1.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomBookingDto {
    @JsonProperty(value = "Id")
    private Long idRoom;
    @JsonProperty(value = "Subject")
    private String subject;
    @JsonProperty(value = "StartTime")
    private String startTime;
    @JsonProperty(value = "Description")
    private String description;
    @JsonProperty(value = "EndTime")
    private String endTime;
    @JsonProperty(value = "Location")
    private String location;
    @JsonProperty(value = "CategoryColor")
    private String color;
    @JsonProperty(value = "OwnerId")
    private Long ownerId;

    public RoomBookingDto() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Long idRoom) {
        this.idRoom = idRoom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
