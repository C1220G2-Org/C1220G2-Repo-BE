package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.StatisticsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatisticsDTOService {
    Page<StatisticsDTO> statisticUser(String startDate, String endDate, Pageable pageable);
}
