package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.MeetingRoom;
import com.codegym.spring_boot_sprint_1.repositories.IMeetingRoomRepository;
import com.codegym.spring_boot_sprint_1.service.IMeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomServiceImpl implements IMeetingRoomService {
    @Autowired
    private IMeetingRoomRepository meetingRoomRepository;

    @Override
    public List<MeetingRoom> findAll() {
        return meetingRoomRepository.findAll();
    }

    @Override
    public MeetingRoom findById(Long id) {
        return meetingRoomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MeetingRoom meetingRoom) {
        meetingRoomRepository.save(meetingRoom);
    }

    @Override
    public List<MeetingRoom> listSearch(String name, Integer floor, Integer capacity, String status) {
        return meetingRoomRepository.listSearch(name, floor, capacity, status);
    }

    @Override
    public List<MeetingRoom> searchFullEmpty(String id, String startDate, String endDate, String fullStartDate, String fullEndDate, int capacity) {
        return meetingRoomRepository.searchFullEmpty(id, startDate, endDate, fullStartDate, fullEndDate, capacity);
    }

    @Override
    public void deleteById(Long idDelete) {
        meetingRoomRepository.deleteById(idDelete);
    }

    public List<MeetingRoom> listSearchEmpty(String id, String startDate, String endDate, String fullStartDate, String fullEndDate, int capacity) {
        return meetingRoomRepository.listSearchEmpty(id, startDate, endDate, fullStartDate, fullEndDate, capacity);
    }

    @Override
    public Page<MeetingRoom> findAll(Pageable pageable) {
        return meetingRoomRepository.findAll(pageable);
    }

    @Override
    public void save(Integer capacity, String name, String color, Integer floor, String status, Long id) {
        meetingRoomRepository.save(capacity, name, color, floor, status, id);
    }

    @Override
    public void saveImage(String link, Long idMeetingRoom) {
        meetingRoomRepository.saveImage(link, idMeetingRoom);
    }

    @Override
    public List<String> getListImageByIdMeetingRoom(Long id) {
        return meetingRoomRepository.getListImageByIdMeetingRoom(id);
    }

    @Override
    public void deleteImageByIdMeetingRoom(Long id) {
        meetingRoomRepository.deleteImageByIdMeetingRoom(id);
    }

    @Override
    public MeetingRoom getByName(String name) {
        return meetingRoomRepository.getByName(name).orElse(null);
    }

    @Override
    public void create(Integer capacity, String name, String color, Integer floor, String status) {
        meetingRoomRepository.create(capacity, name, color, floor, status);
    }

    @Override
    public Integer getAmountUse(Long meeting_room_id) {
        return meetingRoomRepository.getAmountUse(meeting_room_id);
    }

    @Override
    public List<MeetingRoom> listSearchOneField(String keyword) {
        return meetingRoomRepository.listSearch1(keyword);
    }
}