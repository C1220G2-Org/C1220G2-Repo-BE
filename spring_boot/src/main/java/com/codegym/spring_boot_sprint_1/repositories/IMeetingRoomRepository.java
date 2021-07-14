package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.MeetingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    @Query(value = "SELECT * FROM meeting_room WHERE availability = 1 ", nativeQuery = true)
    Page<MeetingRoom> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update meeting_room " +
            " set availability = 0 " +
            " where id = ?1 ", nativeQuery = true)
    void deleteById(Long id);

    @Query(value = "SELECT * from meeting_room where id = ?1 AND availability = 1 ", nativeQuery = true)
    Optional<MeetingRoom> findById(Long id);

    @Query(value = "Select * " +
            " from meeting_room " +
            " where (`name` like %?1%) " +
            " and (floor = ?2 or ?2 is null) " +
            " and (capacity = ?3 or ?3 is null) " +
            " and (`status` = ?4 or ?4 is null) " +
            " and availability = 1 ", nativeQuery = true)
    List<MeetingRoom> listSearch(String name, Integer floor, Integer capacity, String status);

    @Transactional
    @Modifying
    @Query(value = "update meeting_room " +
            " set capacity = ?1, name = ?2, color = ?3, floor = ?4, status = ?5, availability = 1 " +
            " where id = ?6 ", nativeQuery = true)
    void save(Integer capacity, String name, String color, Integer floor, String status, Long id);

    @Transactional
    @Modifying
    @Query(value = "insert into image_of_meeting_room(link, meeting_room_id) " +
            "values " +
            "(?1,?2) ", nativeQuery = true)
    void saveImage(String link, Long idMeetingRoom);

    @Query(value = "select link from image_of_meeting_room where meeting_room_id = ?1 ", nativeQuery = true)
    List<String> getListImageByIdMeetingRoom(Long id);

    @Transactional
    @Modifying
    @Query(value = "Delete FROM image_of_meeting_room where meeting_room_id = ?1 ", nativeQuery = true)
    void deleteImageByIdMeetingRoom(Long id);

    @Query(value = " \tSELECT *FROM meeting_room mr\n" +
            "            left join room_bookings rb on rb.meeting_room_id = mr.id\n" +
            "            where (mr.availability = 1) and (mr.id like %?1%) and\n" +
            "             (mr.capacity is null or mr.capacity >?6) and\n" +
            "            mr.id not in (\n" +
            "            select meeting_room_id\n" +
            "            from room_bookings rb\n" +
            "            where  ((SUBSTRING(start_Date, 1, 10) between  ?2 and ?3 ) or (SUBSTRING(end_date, 1, 10) between  ?2 and ?3 ))\n" +
            "\t\t\t\t\tand id in ( \n" +
            "\t\t\t\t\tselect id from room_bookings\n" +
            "\t\t\t\t\twhere ( CONVERT(SUBSTRING(?4, 12, 50),time) > CONVERT(SUBSTRING(start_Date, 12, 50),time) and CONVERT(SUBSTRING( ?4, 12, 50),time) < CONVERT(SUBSTRING(end_date, 12, 50),time))  \n" +
            "\t\t\t\t\tor ( CONVERT(SUBSTRING( ?5, 12, 50),time) > CONVERT(SUBSTRING(start_Date, 12, 50),time) and CONVERT(SUBSTRING(?5, 12, 50),time) < CONVERT(SUBSTRING(end_date, 12, 50),time) )\n" +
            "                    or ((CONVERT(SUBSTRING( ?4 , 12, 50),time) < CONVERT(SUBSTRING(start_Date, 12, 50),time)) and (CONVERT(SUBSTRING( ?5, 12, 50),time) > CONVERT(SUBSTRING(end_date, 12, 50),time)) )\n" +
            "                    or ((CONVERT(SUBSTRING( ?4 , 12, 50),time) = CONVERT(SUBSTRING(start_Date, 12, 50),time)) and (CONVERT(SUBSTRING( ?5, 12, 50),time) = CONVERT(SUBSTRING(end_date, 12, 50),time)) )\n" +
            "                    )) group by  mr.id;"
            , nativeQuery = true)
    List<MeetingRoom> listSearchEmpty(String id, String startDate, String endDate, String fullStartDate, String fullEndDate, int capacity);

    @Query(value = " \tSELECT *FROM meeting_room mr\n" +
            "            left join room_bookings rb on rb.meeting_room_id = mr.id\n" +
            "            where (mr.availability = 1) and (mr.id = ?1) and\n" +
            "             (mr.capacity is null or mr.capacity >?6) and\n" +
            "            mr.id not in (\n" +
            "            select meeting_room_id\n" +
            "            from room_bookings rb\n" +
            "            where  ((SUBSTRING(start_Date, 1, 10) between  ?2 and ?3 ) or (SUBSTRING(end_date, 1, 10) between  ?2 and ?3 ))\n" +
            "\t\t\t\t\tand id in ( \n" +
            "\t\t\t\t\tselect id from room_bookings\n" +
            "\t\t\t\t\twhere ( CONVERT(SUBSTRING(?4, 12, 50),time) > CONVERT(SUBSTRING(start_Date, 12, 50),time) and CONVERT(SUBSTRING( ?4, 12, 50),time) < CONVERT(SUBSTRING(end_date, 12, 50),time))  \n" +
            "\t\t\t\t\tor ( CONVERT(SUBSTRING( ?5, 12, 50),time) > CONVERT(SUBSTRING(start_Date, 12, 50),time) and CONVERT(SUBSTRING(?5, 12, 50),time) < CONVERT(SUBSTRING(end_date, 12, 50),time) )\n" +
            "                    or ((CONVERT(SUBSTRING( ?4 , 12, 50),time) < CONVERT(SUBSTRING(start_Date, 12, 50),time)) and (CONVERT(SUBSTRING( ?5, 12, 50),time) > CONVERT(SUBSTRING(end_date, 12, 50),time)) )\n" +
            "                    or ((CONVERT(SUBSTRING( ?4 , 12, 50),time) = CONVERT(SUBSTRING(start_Date, 12, 50),time)) and (CONVERT(SUBSTRING( ?5, 12, 50),time) = CONVERT(SUBSTRING(end_date, 12, 50),time)) )\n" +
            "                    )) group by  mr.id;"
            , nativeQuery = true)
    List<MeetingRoom> searchFullEmpty(String id, String startDate, String EndDate, String fullStartDate, String fullEndDate, int capacity);

    @Query(value = "SELECT * FROM meeting_room WHERE availability = 1 ", nativeQuery = true)
    List<MeetingRoom> findAll();

    @Query(value = "SELECT * from meeting_room where `name` = ?1 and availability = 1 ", nativeQuery = true)
    Optional<MeetingRoom> getByName(String name);

    @Query(value = "Select * from meeting_room " +
            "where availability = 1 and (`name` like concat('%',?1,'%') " +
            "or floor = ?1 or ?1 is null  " +
            "or capacity = ?1 " +
            "or`status` like concat('%',?1,'%')) ", nativeQuery = true)
    List<MeetingRoom> listSearch1(String keyword);

    @Transactional
    @Modifying
    @Query(value = "insert into meeting_room " +
            " set capacity = ?1, name = ?2, color = ?3, floor = ?4, status = ?5, availability = 1 ", nativeQuery = true)
    void create(Integer capacity, String name, String color, Integer floor, String status);

    @Query(value = "SELECT count(meeting_room_id) " +
            "FROM room_bookings " +
            "WHERE (end_date < NOW() and meeting_room_id = ?1) " +
            "GROUP BY meeting_room_id ", nativeQuery = true)
    Integer getAmountUse(Long meeting_room_id);
}