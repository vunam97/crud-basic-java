package com.vti.exam.service;

import com.vti.exam.entity.Account;
import com.vti.exam.form.AccountCreateForm;
import com.vti.exam.form.AccountFilterForm;
import com.vti.exam.form.AccountUpdateForm;
import com.vti.exam.repository.IAccountRepository;
import com.vti.exam.repository.IDepartmentRepository;
import com.vti.exam.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository repository;

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<Account> getAll(AccountFilterForm form, Pageable pageable) {
        Specification<Account> spec = AccountSpecification.buildSpec(form);
        return repository.findAll(spec, pageable);
    }

    @Override
    public Account getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(AccountCreateForm form) {
        Account account = mapper.map(form, Account.class);
        repository.save(account);
    }

    @Override
    public void update(AccountUpdateForm form) {
        Account account = mapper.map(form, Account.class);
        repository.save(account);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        repository.deleteAllById(ids);
    }
}
