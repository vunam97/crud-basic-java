package com.vti.exam.form;

import com.vti.exam.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFilterForm {
    private String search;
    private Account.Role role;
}
