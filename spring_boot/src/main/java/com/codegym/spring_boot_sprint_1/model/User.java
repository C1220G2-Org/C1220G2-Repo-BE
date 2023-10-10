package com.codegym.spring_boot_sprint_1.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime startDate;
    private boolean gender;
    private String workPlace;
    private String phoneNumber;
    private String address;
    @Column(unique = true)
    @NotEmpty(message = "Email can not empty")
//    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z]{2,}(\\.[a-z]{2,4}){1,2}$",
//            message = "Email not valid ( ex : abc@abc.abc) ")
    private String email;
    private Boolean status;
    @NotBlank(message = "Can't not empty")
//    @Size(min = 5,max = 45,message = "From 5 to 45 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, @NotBlank(message = "Can't not empty") String name, @NotEmpty(message = "Email can not empty") String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String username, String password, Set<Role> roles, Boolean status, @NotBlank(message = "Can't not empty") String name, @NotEmpty(message = "Email can not empty") String email) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;
        this.name = name;
        this.email = email;
    }

    public User(String username, String password, boolean gender, String workPlace, String phoneNumber,
                String address, @NotEmpty(message = "Email can not empty") String email, Boolean status, @NotBlank(message = "Can't not empty") String name) {
        this.username = username;
        this.password = password;
        this.startDate = LocalDateTime.now();
        this.gender = gender;
        this.workPlace = workPlace;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.status = status;
        this.name = name;
    }

    public Long getId() {
        return id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String description) {
        this.workPlace = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}