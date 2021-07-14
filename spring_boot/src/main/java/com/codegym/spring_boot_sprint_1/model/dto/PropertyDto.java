package com.codegym.spring_boot_sprint_1.model.dto;

import java.util.Arrays;
import java.util.List;

public class PropertyDto {
    private Long id;
    private String name;
    private String detail;
    private Double price;
    private Integer amount;
    private Integer usingProperty;
    private Integer maintenance;
    private Integer availability;
    private String[] image;
    private List<PropertyMeetingRoomDto> propertyMeetingRoomDto;

    public PropertyDto() {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUsingProperty() {
        return usingProperty;
    }

    public void setUsingProperty(Integer usingProperty) {
        this.usingProperty = usingProperty;
    }

    public Integer getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Integer maintenance) {
        this.maintenance = maintenance;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public List<PropertyMeetingRoomDto> getPropertyMeetingRoomDto() {
        return propertyMeetingRoomDto;
    }

    public void setPropertyMeetingRoomDto(List<PropertyMeetingRoomDto> propertyMeetingRoomDto) {
        this.propertyMeetingRoomDto = propertyMeetingRoomDto;
    }

    @Override
    public String toString() {
        return "PropertyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", usingProperty=" + usingProperty +
                ", maintenance=" + maintenance +
                ", availability=" + availability +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}

