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
    public List<Object[]> findAllBookingHistoryOfUser(Long userId) {
        return this.userRepository.findAllBookingHistoryOfUser(userId);
    }

    @Override
    public List<Object[]> searchUserBookingHistory(Long userId, String roomName, String startDate, String endDate, String meetingType, String registrationDate) {
        return this.userRepository.searchUserBookingHistory(userId, roomName, startDate, endDate, meetingType, registrationDate);
    }

    @Override
    public List<Object[]> getAllBookingHistoryOfAllUsers() {
        return this.userRepository.getAllBookingHistoryOfAllUsers();
    }

    @Override
    public List<Object[]> searchBookingHistoryOnAdmin(String roomName, Long userId, String startDate, String endDate, String meetingType, String registrationDate) {
        return this.userRepository.searchBookingHistoryOnAdmin(roomName, userId, startDate, endDate, meetingType, registrationDate);
    }


    //    --------------------------------------------------------------------------------

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAllUser(pageable);
    }

    @Override
    public List<User> searchUser(String keyword) {
        return userRepository.searchUser(keyword);
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
    public void save(String email, String name, String password, String username, Long id, Byte status) {
        userRepository.saveUser(email, name, password, username, id, status);
    }

    @Override
    public void setRole(Long userId, Integer roleId) {
        userRepository.setRoleForUser(userId, roleId);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void updateUser(String email, String name, Long departmentId, String avatar, Long userId) {
        userRepository.updateUser(email, name, departmentId, avatar, userId);
    }

    @Override
    public void updateRole(Integer roleId, Long userId) {
        userRepository.updateRoleForUser(roleId, userId);
    }

    @Override
    public void saveAvatar(String avatar, Long userId) {
        userRepository.saveAvatar(avatar, userId);
    }

    @Override
    public List<Integer> userIsDelete(Long id, String dateTime) {
        return userRepository.userIsDelete(id, dateTime);
    }

    @Override
    public Integer getRoleByUserId(Long userId) {
        return userRepository.getRoleByUserId(userId);
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
