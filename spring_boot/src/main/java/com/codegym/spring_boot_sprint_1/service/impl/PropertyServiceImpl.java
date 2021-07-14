package com.codegym.spring_boot_sprint_1.service.impl;

import com.codegym.spring_boot_sprint_1.model.Property;
import com.codegym.spring_boot_sprint_1.repositories.IPropertyRepository;
import com.codegym.spring_boot_sprint_1.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements IPropertyService {
    @Autowired
    private IPropertyRepository propertyRepository;

    @Override
    public void updateProperty(String name, String detail, Double price, Integer amount, String image, Integer maintenance, Integer availability, Long id) {
        propertyRepository.updateProperty(name, detail, price, amount, image, maintenance, availability, id);
    }

    @Override
    public Page<Property> findAll(Pageable pageable) {
        return propertyRepository.findAllProperty(pageable);
    }

    @Override
    public void save(String name, String detail, Double price, Integer amount, String image, Integer maintenance, Integer availability) {
        propertyRepository.saveProperty(name, detail, price, amount, image, maintenance, availability);
    }

    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findPropertyById(id);
    }

    @Override
    public void deletePropertyById(Long id) {
        propertyRepository.deletePropertyById(id);
    }

    @Override
    public List<Property> searchPropertyByNameAndAmount(String search) {
        return propertyRepository.searchPropertyByNameAndAmount(search);
    }


    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Property> listSearchWithName(String name) {
        return propertyRepository.listSearchWithName(name);
    }

    @Override
    public void updateAmount(Integer amount, Long id) {
        propertyRepository.updateAmount(amount, id);
    }
}
