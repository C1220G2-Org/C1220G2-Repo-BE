package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "count_bookings_per_month")

public class CountBookingsPerMonth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countBookingsPerMonth_id")
    private Long id;
    @JsonProperty(value = "count")
    private int count;
    private String monthYear;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CountBookingsPerMonth() {
    }

    public CountBookingsPerMonth(int count, String monthYear, User user) {
        this.count = count;
        this.monthYear = monthYear;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
