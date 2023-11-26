package com.vti.exam.form;

import com.vti.exam.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentFilterForm {
    private String search;
    private Integer minTotal;
    private Integer maxTotal;
    private Department.Type type;
}
