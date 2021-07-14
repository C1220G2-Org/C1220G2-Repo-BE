package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.StatisticsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsDTORepository extends JpaRepository<StatisticsDTO, Integer> {
    @Query(value = "select meeting_room.id,meeting_room.name, count(meeting_room_id) as count\n" +
            " from room_bookings\n" +
            " right join meeting_room on room_bookings.meeting_room_id=meeting_room.id\n" +
            " where start_date > ?1 and end_date < ?2 or room_bookings.meeting_room_id is null \n" +
            " group by meeting_room_id "
            , nativeQuery = true)
    Page<StatisticsDTO> statistics(String startDate, String endDate, Pageable pageable);
}
