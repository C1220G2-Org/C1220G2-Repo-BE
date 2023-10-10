package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
