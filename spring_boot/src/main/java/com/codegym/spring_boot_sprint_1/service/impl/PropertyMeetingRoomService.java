package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.PropertyMeetingRoom;
import com.codegym.spring_boot_sprint_1.repositories.IPropertyMeetingRoomRepository;
import com.codegym.spring_boot_sprint_1.service.IPropertyMeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyMeetingRoomService implements IPropertyMeetingRoomService {
    @Autowired
    IPropertyMeetingRoomRepository propertyMeetingRoomRepository;


    @Override
    public void deleteByIdMeetingRoom(Long id) {
        propertyMeetingRoomRepository.deleteByIdMeetingRoom(id);
    }

    @Override
    public void save(Long meetingRoomId, Long propertyId, Integer amount) {
        propertyMeetingRoomRepository.save(meetingRoomId, propertyId, amount);
    }

    @Override
    public Integer sumPropertyInRoom(Long id) {
        return propertyMeetingRoomRepository.sumPropertyInRoom(id);
    }

    @Override
    public void deleteByPropertyId(Long id) {
        propertyMeetingRoomRepository.deleteByPropertyId(id);
    }

    @Override
    public List<PropertyMeetingRoom> findPropertyInRoom(Long id) {
        return propertyMeetingRoomRepository.findPropertyInRoom(id);
    }

    @Override
    public List<PropertyMeetingRoom> getListByIdMeetingRoom(Long id) {
        return propertyMeetingRoomRepository.getListByIdMeetingRoom(id);
    }
}
