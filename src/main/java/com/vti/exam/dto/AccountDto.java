package com.vti.exam.dto;

import com.vti.exam.controller.AccountController;
import com.vti.exam.entity.Account;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Getter
@Setter
public class AccountDto extends RepresentationModel<AccountDto>{
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Account.Role role;
    private String departmentName;

    public AccountDto withSelfRel() {
        Link link = linkTo(methodOn(AccountController.class).getById(id)).withSelfRel();
        return add(link);
    }
}
