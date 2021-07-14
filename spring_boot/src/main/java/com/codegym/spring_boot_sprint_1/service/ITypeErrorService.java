package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.TypeError;

import java.util.List;

public interface ITypeErrorService {
    List<TypeError> findAll();

    TypeError findById(Long id);
}
