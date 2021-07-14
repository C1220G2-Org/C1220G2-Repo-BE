package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.PropertyMeetingRoom;

import java.util.List;

public interface IPropertyMeetingRoomService {
    void deleteByIdMeetingRoom(Long id);

    void save(Long meetingRoomId, Long propertyId, Integer amount);

    Integer sumPropertyInRoom(Long id);

    void deleteByPropertyId(Long id);

    List<PropertyMeetingRoom> findPropertyInRoom(Long id);

    List<PropertyMeetingRoom> getListByIdMeetingRoom(Long id);
}