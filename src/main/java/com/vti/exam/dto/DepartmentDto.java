package com.vti.exam.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.vti.exam.controller.AccountController;
import com.vti.exam.controller.DepartmentController;
import com.vti.exam.entity.Department;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class DepartmentDto extends RepresentationModel<DepartmentDto> {
    private int id;
    private String name;
    private int totalMember;
    private Department.Type type;
    private LocalDateTime createdDate;
    private List<AccountDto> accounts;

    public DepartmentDto withSelfRel() {
        if (accounts != null) {
            for (AccountDto account : accounts) {
                Link link = linkTo(methodOn(AccountController.class).getById(account.id)).withSelfRel();
                account.add(link);
            }
        }
        Link link = linkTo(methodOn(DepartmentController.class).getById(id)).withSelfRel();
        return add(link);
    }

    @Getter
    @Setter
    public static class AccountDto extends RepresentationModel<AccountDto>{
        @JsonProperty("accountId")
        private Integer id;
        private String username;

        public AccountDto withSelfRel() {
            Link link = linkTo(methodOn(AccountController.class).getById(id)).withSelfRel();
            return add(link);
        }
    }
}
