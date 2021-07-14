package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Department;
import com.codegym.spring_boot_sprint_1.repositories.IDepartmentRepository;
import com.codegym.spring_boot_sprint_1.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAllDepartment();
    }

    @Override
    public Department findById(Long id) {
        return null;
    }

    @Override
    public void save(Department department) {

    }

    @Override
    public void remove(Long id) {

    }
}
