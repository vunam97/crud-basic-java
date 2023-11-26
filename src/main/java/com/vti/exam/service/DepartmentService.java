package com.vti.exam.service;

import com.vti.exam.entity.Account;
import com.vti.exam.entity.Department;
import com.vti.exam.form.DepartmentCreateForm;
import com.vti.exam.form.DepartmentFilterForm;
import com.vti.exam.form.DepartmentUpdateForm;
import com.vti.exam.repository.IDepartmentRepository;
import com.vti.exam.specification.DepartmentSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private IDepartmentRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Department> getAll(DepartmentFilterForm form, Pageable pageable) {
        Specification<Department> spec = DepartmentSpecification.buildSpec(form);
        return repository.findAll(spec, pageable);
    }

    @Override
    public Department getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void create(DepartmentCreateForm form) {
        Department department = mapper.map(form, Department.class);
        List<Account> accounts = department.getAccounts();
        if (accounts != null) {
            for (Account account : accounts) {
                account.setDepartment(department);
            }
        }
        repository.save(department);
    }

    @Override
    public void update(DepartmentUpdateForm form) {
        Department department = mapper.map(form, Department.class);
        repository.save(department);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
