package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.CourseRatingKey;
import com.codegym.spring_boot_sprint_1.model.PropertyMeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IPropertyMeetingRoomRepository extends JpaRepository<PropertyMeetingRoom, CourseRatingKey> {

    @Transactional
    @Modifying
    @Query(value = "delete " +
            " from property_meeting_room " +
            " where meeting_room_id = ?1 ", nativeQuery = true)
    void deleteByIdMeetingRoom(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete " +
            " from property_meeting_room " +
            " where property_id = ?1 ", nativeQuery = true)
    void deleteByPropertyId(Long id);

    @Query(value = "select * " +
            "from property_meeting_room " +
            "where meeting_room_id = ?1 ", nativeQuery = true)
    List<PropertyMeetingRoom> getListByIdMeetingRoom(Long id);

    @Transactional
    @Modifying
    @Query(value = "insert into property_meeting_room " +
            "values " +
            "(?1,?2,?3) ", nativeQuery = true)
    void save(Long meetingRoomId, Long propertyId, Integer amount);

    @Query(value = "SELECT sum(amount_in_room) FROM property_meeting_room where property_id = ?1 ", nativeQuery = true)
    Integer sumPropertyInRoom(Long id);

    @Query(value = "SELECT * FROM property_meeting_room where property_id= ?1", nativeQuery = true)
    List<PropertyMeetingRoom> findPropertyInRoom(Long id);

}
