package com.codegym.spring_boot_sprint_1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CountBookingsPerMonth> countBookingsPerMonthList;

    @OneToMany(mappedBy = "userFeedback")
    @JsonBackReference(value = "userFeedback")
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user")
    private Set<Notification> notifications;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    private Boolean status;

    @NotBlank(message = "Can't not empty")
//    @Size(min = 5,max = 45,message = "From 5 to 45 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Email can not empty")
//    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z]{2,}(\\.[a-z]{2,4}){1,2}$",
//            message = "Email not valid ( ex : abc@abc.abc) ")
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<RoomBooking> roomBookings;

    public User() {
    }

    public User(String username, String password, @NotBlank(message = "Can't not empty") String name, @NotEmpty(message = "Email can not empty") String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String username, String password, Department department, Set<Role> roles, @NotBlank(message = "Can't not empty") String name, @NotEmpty(message = "Email can not empty") String email) {
        this.username = username;
        this.password = password;
        this.department = department;
        this.roles = roles;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CountBookingsPerMonth> getCountBookingsPerMonthList() {
        return countBookingsPerMonthList;
    }

    public void setCountBookingsPerMonthList(List<CountBookingsPerMonth> countBookingsPerMonthList) {
        this.countBookingsPerMonthList = countBookingsPerMonthList;
    }

    public Set<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(Set<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}