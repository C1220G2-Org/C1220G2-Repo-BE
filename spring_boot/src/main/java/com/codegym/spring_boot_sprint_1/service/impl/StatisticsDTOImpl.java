package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.StatisticsDTO;
import com.codegym.spring_boot_sprint_1.repositories.StatisticsDTORepository;
import com.codegym.spring_boot_sprint_1.service.StatisticsDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StatisticsDTOImpl implements StatisticsDTOService {

    @Autowired
    private StatisticsDTORepository statisticsDTORepository;

    @Override
    public Page<StatisticsDTO> statisticUser(String startDate, String endDate, Pageable pageable) {
        return statisticsDTORepository.statistics(startDate, endDate, pageable);
    }
}
