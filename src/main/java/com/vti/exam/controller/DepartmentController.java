package com.vti.exam.controller;

import com.vti.exam.dto.DepartmentDto;
import com.vti.exam.entity.Department;
import com.vti.exam.form.DepartmentCreateForm;
import com.vti.exam.form.DepartmentFilterForm;
import com.vti.exam.form.DepartmentUpdateForm;
import com.vti.exam.service.IDepartmentService;
import com.vti.exam.validate.DepartmentIdExists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<DepartmentDto> getAll(DepartmentFilterForm form, Pageable pageable) {
        Page<Department> departments = service.getAll(form, pageable);
        return departments.map(department -> mapper.map(department, DepartmentDto.class));
    }

    @GetMapping("/{id}")
    public DepartmentDto getById(@PathVariable("id") @DepartmentIdExists Integer id) {
        Department department = service.getById(id);
        return mapper.map(department, DepartmentDto.class);
    }

    @PostMapping
    public void create(@RequestBody @Valid DepartmentCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") @DepartmentIdExists Integer id, @RequestBody DepartmentUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
         service.deleteById(id);
    }
}
