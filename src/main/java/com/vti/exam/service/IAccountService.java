package com.vti.exam.service;


import com.vti.exam.entity.Account;
import com.vti.exam.form.AccountCreateForm;
import com.vti.exam.form.AccountFilterForm;
import com.vti.exam.form.AccountUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    Page<Account> getAll(AccountFilterForm form, Pageable pageable);

    Account getById(Integer id);

    void create(AccountCreateForm form);
    void update(AccountUpdateForm form);
    void deleteById(Integer id);

    void deleteAllById(List<Integer> ids);
}
