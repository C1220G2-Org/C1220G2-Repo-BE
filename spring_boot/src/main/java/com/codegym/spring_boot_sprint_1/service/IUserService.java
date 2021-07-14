package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    //    Code of khahq

    List<Object[]> findAllBookingHistoryOfUser(Long userId);

    List<Object[]> searchUserBookingHistory(Long userId, String roomName, String startDate, String endDate, String meetingType, String registrationDate);

    List<Object[]> getAllBookingHistoryOfAllUsers();

    List<Object[]> searchBookingHistoryOnAdmin(String roomName, Long userId,
                                               String startDate, String endDate,
                                               String meetingType, String registrationDate);

//    -------------------------------------------------------------

    Page<User> findAll(Pageable pageable);

    List<User> findAllByList();

    List<User> searchUser(String keyword);

    User findById(Long id);

    User findUserByEmail(String email);

    boolean isEmailExist(String email);

    Boolean isUsernameExist(String username);

    void save(String email, String name, String password, String username, Long id, Byte status);

    void setRole(Long userId, Integer roleId);

    void updateUserPassword(String password, Long userId);

    void deleteUser(Long id);

    void updateUser(String email, String name, Long departmentId, String avatar, Long userId);

    void updateRole(Integer roleId, Long userId);

    void saveAvatar(String avatar, Long userId);

    List<Integer> userIsDelete(Long id, String dateTime);

    Integer getRoleByUserId(Long userId);
}
