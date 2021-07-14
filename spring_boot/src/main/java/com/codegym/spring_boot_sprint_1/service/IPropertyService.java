package com.codegym.spring_boot_sprint_1.service;


import com.codegym.spring_boot_sprint_1.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPropertyService {

    void updateProperty(String name, String detail, Double price, Integer amount, String image, Integer maintenance, Integer availability, Long id);

    Page<Property> findAll(Pageable pageable);

    void save(String name, String detail, Double price, Integer amount, String image, Integer maintenance, Integer availability);

    Property findPropertyById(Long id);

    void deletePropertyById(Long id);

    List<Property> searchPropertyByNameAndAmount(String search);

    List<Property> findAll();

    Property findById(Long id);

    List<Property> listSearchWithName(String name);

    void updateAmount(Integer amount, Long id);
}
