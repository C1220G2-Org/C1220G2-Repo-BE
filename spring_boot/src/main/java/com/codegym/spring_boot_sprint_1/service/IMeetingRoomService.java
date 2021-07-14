package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.MeetingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMeetingRoomService {
    void deleteById(Long idDelete);

    List<MeetingRoom> findAll();

    MeetingRoom findById(Long id);

    void save(MeetingRoom meetingRoom);

    List<MeetingRoom> listSearchEmpty(String id, String startDate, String endDate, String fullStartDate, String fullEndDate, int capacity);

    List<MeetingRoom> listSearch(String name, Integer floor, Integer capacity, String status);

    List<MeetingRoom> searchFullEmpty(String id, String startDate, String endDate, String fullStartDate, String fullEndDate, int capacity);

    Page<MeetingRoom> findAll(Pageable pageable);

    void save(Integer capacity, String name, String color, Integer floor, String status, Long id);

    void saveImage(String link, Long idMeetingRoom);

    List<String> getListImageByIdMeetingRoom(Long id);

    void deleteImageByIdMeetingRoom(Long id);

    MeetingRoom getByName(String name);

    void create(Integer capacity, String name, String color, Integer floor, String status);

    Integer getAmountUse(Long meeting_room_id);

    List<MeetingRoom> listSearchOneField(String keyword);
}