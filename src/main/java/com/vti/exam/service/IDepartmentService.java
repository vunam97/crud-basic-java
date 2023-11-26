package com.vti.exam.service;

import com.vti.exam.entity.Department;
import com.vti.exam.form.DepartmentCreateForm;
import com.vti.exam.form.DepartmentFilterForm;
import com.vti.exam.form.DepartmentUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
    Page<Department> getAll(DepartmentFilterForm form, Pageable pageable);

    Department getById(Integer id);

    void create(DepartmentCreateForm form);
    void update(DepartmentUpdateForm form);
    void deleteById(Integer id);
}
