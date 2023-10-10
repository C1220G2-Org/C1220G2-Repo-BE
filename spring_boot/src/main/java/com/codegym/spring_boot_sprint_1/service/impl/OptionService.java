package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Option;
import com.codegym.spring_boot_sprint_1.repositories.OptionRepository;
import com.codegym.spring_boot_sprint_1.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService implements IGeneralService<Option> {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public Page<Option> findAll(Pageable pageable) {
        return optionRepository.findAll(pageable);
    }

    @Override
    public Option findById(Long id) {
        return optionRepository.findById(id).orElse(null);
    }

    @Override
    public Option save(Option category) {
        return optionRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        optionRepository.deleteById(id);
    }
}
