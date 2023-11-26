package com.vti.exam.form;

import com.vti.exam.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdateForm {
    private int id;
    private String password;
    private String firstName;
    private String lastName;
    private Account.Role role;
    private Integer departmentId;
}
