package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class TypeError implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_error")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "typeError")
    @JsonBackReference(value = "typeError")
    private Set<Feedback> feedbackSet;

    public TypeError(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idTypeError) {
        this.id = idTypeError;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Feedback> getFeedbackSet() {
        return feedbackSet;
    }

    public void setFeedbackSet(Set<Feedback> feedbackSet) {
        this.feedbackSet = feedbackSet;
    }

    public TypeError() {
    }
}
