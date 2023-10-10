package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.User;
import com.codegym.spring_boot_sprint_1.repositories.IUserRepository;
import com.codegym.spring_boot_sprint_1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public boolean isEmailExist(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        return user == null;
    }

    @Override
    public Boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void setRole(Long userId, Integer roleId) {
        userRepository.setRoleForUser(userId, roleId);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateRole(Integer roleId, Long userId) {
        userRepository.updateRoleForUser(roleId, userId);
    }

    @Override
    public void updateUserPassword(String password, Long userId) {
        userRepository.updateUserPassword(password, userId);
    }

    @Override
    public List<User> findAllByList() {
        return userRepository.findAll();
    }
}
