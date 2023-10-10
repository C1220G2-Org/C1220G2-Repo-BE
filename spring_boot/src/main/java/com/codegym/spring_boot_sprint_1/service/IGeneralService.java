package com.codegym.spring_boot_sprint_1.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGeneralService<E> {
    static final int ENTRIES_PER_PAGE = 5;

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E findById(Long id);

    E save(E e);

    void deleteById(Long id);
}
