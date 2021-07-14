package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Role;
import com.codegym.spring_boot_sprint_1.repositories.IRoleRepository;
import com.codegym.spring_boot_sprint_1.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllRole();
    }
}
