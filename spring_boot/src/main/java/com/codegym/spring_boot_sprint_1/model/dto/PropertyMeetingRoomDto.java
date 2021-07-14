package com.codegym.spring_boot_sprint_1.model.dto;

public class PropertyMeetingRoomDto {
    private String name;
    private Integer amountInRoom;

    public PropertyMeetingRoomDto() {
    }

    public PropertyMeetingRoomDto(String name, Integer amountInRoom) {
        this.name = name;
        this.amountInRoom = amountInRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountInRoom() {
        return amountInRoom;
    }

    public void setAmountInRoom(Integer amountInRoom) {
        this.amountInRoom = amountInRoom;
    }

    @Override
    public String toString() {
        return "PropertyMeetingRoomDto{" +
                "name='" + name + '\'' +
                ", amountInRoom=" + amountInRoom +
                '}';
    }
}
