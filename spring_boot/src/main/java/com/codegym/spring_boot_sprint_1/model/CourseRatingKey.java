package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseRatingKey implements Serializable {


    @Column(name = "meeting_room_id")
    Long meetingRoomId;


    @Column(name = "property_id")
    Long propertyId;

    public CourseRatingKey(Long meetingRoomId, Long propertyId) {
        this.meetingRoomId = meetingRoomId;
        this.propertyId = propertyId;
    }

    public CourseRatingKey() {
    }

    public Long getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(Long meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRatingKey that = (CourseRatingKey) o;
        return Objects.equals(meetingRoomId, that.meetingRoomId) &&
                Objects.equals(propertyId, that.propertyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingRoomId, propertyId);
    }
}
