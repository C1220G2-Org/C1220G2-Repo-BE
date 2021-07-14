package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class ImageOfMeetingRoom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String link;

    @ManyToOne
    @JoinColumn(name = "meeting_room_id", nullable = false)
    private MeetingRoom meetingRoom;

    public ImageOfMeetingRoom() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
