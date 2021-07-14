package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.CountBookingsPerMonth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountBookingsPerMonthRepository extends JpaRepository<CountBookingsPerMonth, Long> {
    CountBookingsPerMonth findByUser_IdAndAndMonthYearContains(Long id, String monthYear);
}
