package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int correctOption;
    private int type;
    private LocalDateTime dateCreated;
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "question_option",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> options;

    public Question() {
    }

    public Question(String title, List<Option> options, int correctOption, LocalDateTime dateCreated, boolean enabled, Category category) {
        this.title = title;
        this.options = options;
        this.correctOption = correctOption;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.category = category;
    }

    public Question(String title, int correctOption, int type, LocalDateTime dateCreated, boolean enabled, Category category) {
        this.title = title;
        this.correctOption = correctOption;
        this.type = type;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.category = category;
    }

    public Question(String title, int correctOption, LocalDateTime dateCreated, boolean enabled, Category category) {
        this.title = title;
        this.correctOption = correctOption;
        this.dateCreated = dateCreated;
        this.enabled = enabled;
        this.category = category;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> optionList) {
        this.options = optionList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
