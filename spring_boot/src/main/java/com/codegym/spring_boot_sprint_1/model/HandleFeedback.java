package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class HandleFeedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "handle_feedback_id")
    private Long id;

    @Column(name = "handle_feedback_content")
    private String content;
    private String image;

    @OneToOne()
    @JoinColumn(name = "id_feedback", referencedColumnName = "id_feedback")
    private Feedback feedback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public HandleFeedback() {
    }
}
