package com.vti.exam.repository;

import com.vti.exam.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    boolean existsByName(String name);
}
