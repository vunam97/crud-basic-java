package com.vti.exam.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class AccountCreateForm {
    @NotBlank(message = "Tên tài khoản không được để trống")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    private String firstName;
    private String lastName;
    @Pattern(
            regexp = "ADMIN|EMPLOYEE|MANAGER",
            message = "Loại vai trò không hợp lệ"
    )
    private String role;
    private int departmentId;
}
