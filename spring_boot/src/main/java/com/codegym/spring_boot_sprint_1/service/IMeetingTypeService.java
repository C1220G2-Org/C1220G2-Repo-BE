package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.MeetingType;

import java.util.List;
import java.util.Optional;

public interface IMeetingTypeService {

    List<MeetingType> findAll();


    Optional<MeetingType> findById(Long id);

    MeetingType save(MeetingType meetingType);

    void deleteById(Long idDelete);
}
