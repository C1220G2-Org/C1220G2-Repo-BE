package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Department findById(Long id);

    void save(Department user);

    void remove(Long id);
}
