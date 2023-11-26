package com.vti.exam.form;

import com.vti.exam.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdateForm {
    private int id;
    private String name;
    private int totalMember;
    private Department.Type type;
}
