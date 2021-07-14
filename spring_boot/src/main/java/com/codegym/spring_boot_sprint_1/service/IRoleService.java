package com.codegym.spring_boot_sprint_1.service;

import com.codegym.spring_boot_sprint_1.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
}
