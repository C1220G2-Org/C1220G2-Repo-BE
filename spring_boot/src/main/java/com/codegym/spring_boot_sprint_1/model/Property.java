package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String detail;
    private Double price;
    private Integer amount;

    @Column(name = "using_property", columnDefinition = "integer default  0")
    private Integer usingProperty;

    private Integer maintenance;
    private Integer availability;
    @Column(columnDefinition = "Mediumtext")
    private String image;

    @OneToMany(mappedBy = "property")
    @JsonIgnore
    private Set<PropertyMeetingRoom> ratings;

    public Property() {
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

    public Set<PropertyMeetingRoom> getRatings() {
        return ratings;
    }

    public void setRatings(Set<PropertyMeetingRoom> ratings) {
        this.ratings = ratings;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
