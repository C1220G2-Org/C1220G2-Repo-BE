package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    Page<User> findAll(Pageable pageable);

    List<User> findAllByList();

    User findById(Long id);

    User findUserByEmail(String email);

    boolean isEmailExist(String email);

    Boolean isUsernameExist(String username);

    void save(User user);

    void setRole(Long userId, Integer roleId);

    void updateUserPassword(String password, Long userId);

    void deleteUser(Long id);

    void updateRole(Integer roleId, Long userId);
}
