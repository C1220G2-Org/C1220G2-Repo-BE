package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.TypeError;
import com.codegym.spring_boot_sprint_1.repositories.ITypeErrorRepository;
import com.codegym.spring_boot_sprint_1.service.ITypeErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeErrorServiceImpl implements ITypeErrorService {
    @Autowired
    ITypeErrorRepository typeErrorRepository;

    @Override
    public List<TypeError> findAll() {
        return typeErrorRepository.findAllTypeError();
    }

    @Override
    public TypeError findById(Long id) {
        return typeErrorRepository.findById(id).orElse(null);
    }
}
