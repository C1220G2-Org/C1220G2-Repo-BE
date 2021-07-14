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

    //khahq's code
    //    Getting the booking history of the user.
    @Query(value = "select distinct meeting_room.`name`, room_bookings.content, room_bookings.registration_date,\n" +
            "\tmeeting_room.floor, room_bookings.end_date, room_bookings.start_date, meeting_types.meeting_type_name, room_bookings.id, room_bookings.user_id\n" +
            "from room_bookings\n" +
            "inner join users on room_bookings.user_id = :userId\n" +
            "inner join meeting_room on room_bookings.meeting_room_id = meeting_room.id\n" +
            "inner join meeting_types on room_bookings.meeting_type_id = meeting_types.id\n" +
            "order by room_bookings.registration_date", nativeQuery = true)
    List<Object[]> findAllBookingHistoryOfUser(@Param("userId") Long userId);

    //    Search bookings of the user.
    @Query(value = "select distinct meeting_room.`name`, room_bookings.content, room_bookings.registration_date,\n" +
            "            meeting_room.floor, room_bookings.end_date, room_bookings.start_date, meeting_types.meeting_type_name, room_bookings.id, room_bookings.user_id\n" +
            "            from room_bookings\n" +
            "            inner join users on room_bookings.user_id = ?1\n" +
            "            inner join meeting_room on room_bookings.meeting_room_id = meeting_room.id\n" +
            "            inner join meeting_types on room_bookings.meeting_type_id = meeting_types.id\n" +
            "where (meeting_room.`name` like %?2%)\n" +
            "and (?3 is null or room_bookings.start_date >= ?3)\n" +
            "and (?4 is null or room_bookings.end_date <= ?4)\n" +
            "and (?5 is null or meeting_types.meeting_type_name = ?5)\n" +
            "and (?6 is null or substring(room_bookings.registration_date, 1, 10) = ?6)\n" +
            "order by room_bookings.registration_date;"
            , nativeQuery = true)
    List<Object[]> searchUserBookingHistory(Long userId, String roomName,
                                            String startDate, String endDate,
                                            String meetingType, String registrationDate);

    //    Search bookings on admin account.
    @Query(value = "select distinct meeting_room.`name`, room_bookings.content, room_bookings.registration_date,\n" +
            "                        meeting_room.floor, room_bookings.end_date, room_bookings.start_date, meeting_types.meeting_type_name, room_bookings.id, room_bookings.user_id\n" +
            "                        from room_bookings\n" +
            "                        inner join meeting_room on room_bookings.meeting_room_id = meeting_room.id\n" +
            "                        inner join meeting_types on room_bookings.meeting_type_id = meeting_types.id\n" +
            "            where (meeting_room.`name` like %?1%)\n" +
            "            and (?2 is null or room_bookings.user_id = ?2)\n" +
            "            and (?3 is null or room_bookings.start_date >= ?3)\n" +
            "            and (?4 is null or room_bookings.end_date <= ?4)\n" +
            "            and (?5 is null or meeting_types.meeting_type_name = ?5)\n" +
            "            and (?6 is null or substring(room_bookings.registration_date, 1, 10) = ?6)\n" +
            "order by room_bookings.registration_date"
            , nativeQuery = true)
    List<Object[]> searchBookingHistoryOnAdmin(String roomName, Long userId,
                                               String startDate, String endDate,
                                               String meetingType, String registrationDate);

    //    Code of khahq
    @Query(value = "select distinct meeting_room.`name`, room_bookings.content, room_bookings.registration_date,\n" +
            "\tmeeting_room.floor, room_bookings.end_date, room_bookings.start_date, meeting_types.meeting_type_name, room_bookings.id, room_bookings.user_id\n" +
            "from room_bookings\n" +
            "inner join meeting_room on room_bookings.meeting_room_id = meeting_room.id\n" +
            "inner join meeting_types on room_bookings.meeting_type_id = meeting_types.id\n" +
            "order by room_bookings.registration_date;"
            , nativeQuery = true)
    List<Object[]> getAllBookingHistoryOfAllUsers();


//    ---------------------------------------------------------------------------------
//    ---------------------------------------------------------------------------------


    //Code of dongthg

    //Get list of users
    @Query(value = "SELECT * " +
            "FROM users " +
            "INNER JOIN user_role " +
            "ON users.user_id=user_role.user_id " +
            "INNER JOIN roles " +
            "ON user_role.role_id=roles.role_id " +
            "INNER JOIN department " +
            "ON users.department_id=department.department_id " +
            "WHERE users.status = 1 ", nativeQuery = true)
    Page<User> findAllUser(Pageable pageable);

    //Get list of users by search
    @Query(value = "SELECT * " +
            "FROM users " +
            "INNER JOIN department " +
            "ON users.department_id=department.department_id " +
            "WHERE (users.username LIKE %?1% " +
            "OR users.email LIKE %?1% " +
            "OR department.name LIKE %?1%) " +
            "AND users.status = 1 ", nativeQuery = true)
    List<User> searchUser(String keyword);

    //Find user by id
    @Query(value = "SELECT * " +
            "FROM users " +
            "WHERE users.user_id = ?1 AND users.status = 1 ", nativeQuery = true)
    Optional<User> findById(Long id);

    //Add new user
    @Modifying
    @Transactional
    @Query(value = " INSERT INTO users (email,`name`,`password`,username,department_id,status) " +
            "VALUE " +
            "(?1,?2,?3,?4,?5,?6) ", nativeQuery = true)
    void saveUser(String email, String name, String password, String username, Long departmentId, Byte userStatus);

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

    //Delete user
    @Modifying
    @Transactional
    @Query(value = " UPDATE users " +
            "SET users.status = 0 " +
            "WHERE users.user_id = ?1 ", nativeQuery = true)
    void deleteUser(Long userId);

    //Get list of booking from user's id
    @Query(value = "SELECT user_id " +
            "FROM room_bookings " +
            "WHERE room_bookings.user_id = ?1 " +
            "AND (start_Date) > ?2 ", nativeQuery = true)
    List<Integer> userIsDelete(Long id, String dateTime);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}
