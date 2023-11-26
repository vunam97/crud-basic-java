package com.vti.exam.controller;

import com.vti.exam.dto.AccountDto;
import com.vti.exam.entity.Account;
import com.vti.exam.form.AccountCreateForm;
import com.vti.exam.form.AccountFilterForm;
import com.vti.exam.form.AccountUpdateForm;
import com.vti.exam.service.IAccountService;
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
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public Page<AccountDto> getAll(AccountFilterForm form, Pageable pageable) {
        Page<Account> accounts = service.getAll(form, pageable);
        return accounts.map(account -> mapper.map(account, AccountDto.class).withSelfRel());
    }

    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable("id") Integer id) {
        Account account = service.getById(id);
        return mapper.map(account, AccountDto.class).withSelfRel();
    }

    @PostMapping
    public void create(@RequestBody @Valid AccountCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody AccountUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
         service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllById(@RequestBody List<Integer> ids) {
        service.deleteAllById(ids);
    }
}
