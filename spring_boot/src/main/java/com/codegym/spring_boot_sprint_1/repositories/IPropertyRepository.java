package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface IPropertyRepository extends JpaRepository<Property, Long> {
    @Query(value = "select * from property", nativeQuery = true)
    Page<Property> findAllProperty(Pageable pageable);

    @Query(value = "select * from property where `name` like concat('%', ?1, '%') OR (?1 IS NULL  OR amount =?1)  ", nativeQuery = true)
    List<Property> searchPropertyByNameAndAmount(String search);

    @Transactional
    @Modifying
    @Query(value = "insert into property (`name`,detail,price,amount,image,maintenance,availability ) " +
            "values (?1,?2,?3,?4,?5,?6,?7) ", nativeQuery = true)
    void saveProperty(String name, String detail, Double price, Integer amount, String image, Integer maintenance, Integer availability);

    @Query(value = "select * from property where id = ?1", nativeQuery = true)
    Property findPropertyById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE property SET name = ?1, detail = ?2,  price = ?3, amount = ?4,image = ?5, maintenance =?6,availability=?7 " +
            " WHERE id = ?8 ", nativeQuery = true)
    void updateProperty(String name, String detail, Double price, Integer amount, String image, Integer maintenance, Integer availability, Long id);

    //    Tuấn code ------------
    @Transactional
    @Modifying
    @Query(value = "delete from property where id = ?1", nativeQuery = true)
    void deletePropertyById(Long id);

    @Query(value = "Select * " +
            " from property " +
            " where (`name` like %?1%) ", nativeQuery = true)
    List<Property> listSearchWithName(String name);

    @Transactional
    @Modifying
    @Query(value = "update property " +
            "set availability = ?1 " +
            "where id = ?2 ", nativeQuery = true)
    void updateAmount(Integer amount, Long id);
}
