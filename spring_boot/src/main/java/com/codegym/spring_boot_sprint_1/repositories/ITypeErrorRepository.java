package com.codegym.spring_boot_sprint_1.repositories;

import com.codegym.spring_boot_sprint_1.model.TypeError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITypeErrorRepository extends JpaRepository<TypeError, Long> {
    @Query(value = "SELECT * FROM type_error ", nativeQuery = true)
    List<TypeError> findAllTypeError();
}
