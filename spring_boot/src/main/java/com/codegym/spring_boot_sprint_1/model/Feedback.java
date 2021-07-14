package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private Long id;

    @NotBlank
    private String feedbackTitle;
    private String feedbackContent;
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_type_error")
    private TypeError typeError;
    private String feedbackTime;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userFeedback;

    @ManyToOne
    @JoinColumn(name = "feedback_type_id", nullable = false)
    private FeedbackType feedbackType;

    @OneToOne(mappedBy = "feedback", cascade = CascadeType.ALL)
    private HandleFeedback handleFeedback;

    @ManyToOne
    @JoinColumn(name = "meeting_room_id")
    private MeetingRoom meetingRoom;

    public Feedback() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public TypeError getTypeError() {
        return typeError;
    }

    public void setTypeError(TypeError typeError) {
        this.typeError = typeError;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(User userFeedback) {
        this.userFeedback = userFeedback;
    }

    public FeedbackType getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public HandleFeedback getHandleFeedback() {
        return handleFeedback;
    }

    public void setHandleFeedback(HandleFeedback handleFeedback) {
        this.handleFeedback = handleFeedback;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }
}
