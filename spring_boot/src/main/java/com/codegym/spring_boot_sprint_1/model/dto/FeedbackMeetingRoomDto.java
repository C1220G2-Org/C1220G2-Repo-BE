package com.codegym.spring_boot_sprint_1.model.dto;

public class FeedbackMeetingRoomDto {
    private Long id;

    private String name;

    public FeedbackMeetingRoomDto() {
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
}
