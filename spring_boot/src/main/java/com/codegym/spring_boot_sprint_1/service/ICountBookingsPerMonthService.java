package com.codegym.spring_boot_sprint_1.service;


import com.codegym.spring_boot_sprint_1.model.CountBookingsPerMonth;

import java.util.List;
import java.util.Optional;

public interface ICountBookingsPerMonthService {

    List<CountBookingsPerMonth> findAll();


    Optional<CountBookingsPerMonth> findById(Long id);

    CountBookingsPerMonth save(CountBookingsPerMonth meetingType);

    void deleteById(Long idDelete);

    CountBookingsPerMonth findByUserAndAndMonthYearContains(Long id, String monthYear);
}
