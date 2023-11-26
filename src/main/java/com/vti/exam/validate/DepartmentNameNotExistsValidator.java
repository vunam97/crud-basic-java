package com.vti.exam.validate;

import com.vti.exam.repository.IDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepartmentNameNotExistsValidator implements ConstraintValidator<DepartmentNameNotExists, String> {
    @Autowired
    private IDepartmentRepository repository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !repository.existsByName(name);
    }
}
