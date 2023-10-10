package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    //Find user by id
    @Query(value = "SELECT * " +
            "FROM users " +
            "WHERE users.user_id = ?1 AND users.status = 1 ", nativeQuery = true)
    Optional<User> findById(Long id);

    //Add new user
    @Modifying
    @Transactional
    @Query(value = " INSERT INTO users (email,`name`,`password`,username,status) " +
            "VALUE " +
            "(?1,?2,?3,?4,?5) ", nativeQuery = true)
    void saveUser(String email, String name, String password, String username, Byte userStatus);

    //Set role for user by userId
    @Modifying
    @Transactional
    @Query(value = " INSERT INTO user_role (user_id, role_id) " +
            "VALUE " +
            "(?1,?2) ", nativeQuery = true)
    void setRoleForUser(Long userId, Integer roleId);

    //Edit role for user
    @Modifying
    @Transactional
    @Query(value = " UPDATE user_role " +
            "SET user_role.role_id = ?1 " +
            "WHERE user_role.user_id = ?2 ", nativeQuery = true)
    void updateRoleForUser(Integer roleId, Long userId);

    //Get role by user id
    @Query(value = "SELECT * " +
            "FROM user_role " +
            "WHERE user_role.user_id = ?1 ", nativeQuery = true)
    Integer getRoleByUserId(Long userId);

    //Find user by email
    @Query(value = "SELECT * " +
            "FROM users " +
            "WHERE users.email = ?1 AND users.status = 1 ", nativeQuery = true)
    Optional<User> findUserByEmail(String email);

    //Edit user by param
    @Modifying
    @Transactional
    @Query(value = " UPDATE users " +
            "SET users.email = ?1, " +
            "users.name = ?2, " +
            "users.department_id = ?3, " +
            "users.avatar = ?4 " +
            "WHERE users.user_id = ?5 ", nativeQuery = true)
    void updateUser(String email, String name, Long departmentId, String avatar, Long userId);

    //Save avatar
    @Modifying
    @Transactional
    @Query(value = " UPDATE users " +
            "SET users.avatar = ?1 " +
            "WHERE users.user_id = ?2 ", nativeQuery = true)
    void saveAvatar(String avatar, Long userId);

    //Change user's password
    @Modifying
    @Transactional
    @Query(value = " UPDATE users " +
            "SET users.password = ?1 " +
            "WHERE users.user_id = ?2 ", nativeQuery = true)
    void updateUserPassword(String password, Long userId);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}
