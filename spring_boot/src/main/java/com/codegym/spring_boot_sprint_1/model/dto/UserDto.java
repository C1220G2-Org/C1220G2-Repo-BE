package com.codegym.spring_boot_sprint_1.model.dto;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Long department;
    private Integer roles;
    private String name;
    private String email;
    private String avatar;

    public UserDto() {
    }

    public UserDto(String username, String password, Long department, Integer roles, String name, String email, String avatar) {
        this.username = username;
        this.password = password;
        this.department = department;
        this.roles = roles;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRole(Integer roles) {
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

    public void setRoles(Integer roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", department=" + department +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
