package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.MeetingType;
import com.codegym.spring_boot_sprint_1.repositories.IMeetingTypeRepository;
import com.codegym.spring_boot_sprint_1.service.IMeetingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingTypeServiceImpl implements IMeetingTypeService {

    @Autowired
    private IMeetingTypeRepository meetingTypeRepository;


    @Override
    public List<MeetingType> findAll() {
        return meetingTypeRepository.findAll();
    }

    @Override
    public Optional<MeetingType> findById(Long id) {
        return meetingTypeRepository.findById(id);
    }

    @Override
    public MeetingType save(MeetingType meetingType) {
        return meetingTypeRepository.save(meetingType);
    }


    @Override
    public void deleteById(Long idDelete) {
        meetingTypeRepository.deleteById(idDelete);
    }
}
