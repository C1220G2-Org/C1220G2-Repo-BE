package com.codegym.spring_boot_sprint_1.model.dto;


import java.util.List;

public class MeetingRoomDto {
    private Long id;

    private String name;
    private Integer floor;
    private Integer capacity;
    private String color;
    private String status;
    private List<String> images;
    private List<PropertyInRoomDto> propertyDtoList;
    private Integer amountUse;

    public MeetingRoomDto() {
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<PropertyInRoomDto> getPropertyDtoList() {
        return propertyDtoList;
    }

    public void setPropertyDtoList(List<PropertyInRoomDto> propertyDtoList) {
        this.propertyDtoList = propertyDtoList;
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

    @Override
    public String toString() {
        return "MeetingRoomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", floor=" + floor +
                ", capacity=" + capacity +
                ", color='" + color + '\'' +
                ", status='" + status + '\'' +
                ", propertyDtoList=" + propertyDtoList +
                '}';
    }
}

