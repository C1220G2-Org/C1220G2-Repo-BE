package com.codegym.spring_boot_sprint_1.model.dto;

import com.codegym.spring_boot_sprint_1.model.User;

public class CountBookingsPerMonthDto {
    private Long id;
    private int count;
    private String monthYear;
    private User user;

    public CountBookingsPerMonthDto(int count, String monthYear, User user) {
        this.count = count;
        this.monthYear = monthYear;
        this.user = user;
    }

    public CountBookingsPerMonthDto() {
    }

    public CountBookingsPerMonthDto(Long id, int count, String monthYear, User user) {
        this.id = id;
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
