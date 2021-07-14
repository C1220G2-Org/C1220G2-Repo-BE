package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.CountBookingsPerMonth;
import com.codegym.spring_boot_sprint_1.repositories.ICountBookingsPerMonthRepository;
import com.codegym.spring_boot_sprint_1.service.ICountBookingsPerMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountBookingsPerMonthServiceImpl implements ICountBookingsPerMonthService {

    @Autowired
    ICountBookingsPerMonthRepository countBookingsPerMonthRepository;

    @Override
    public List<CountBookingsPerMonth> findAll() {
        return countBookingsPerMonthRepository.findAll();
    }

    @Override
    public Optional<CountBookingsPerMonth> findById(Long id) {
        return countBookingsPerMonthRepository.findById(id);
    }

    @Override
    public CountBookingsPerMonth save(CountBookingsPerMonth countBookingsPerMonth) {
        return countBookingsPerMonthRepository.save(countBookingsPerMonth);
    }

    @Override
    public void deleteById(Long idDelete) {
        countBookingsPerMonthRepository.deleteById(idDelete);
    }

    @Override
    public CountBookingsPerMonth findByUserAndAndMonthYearContains(Long id, String monthYear) {
        return countBookingsPerMonthRepository.findByUser_IdAndAndMonthYearContains(id, monthYear);
    }
}
