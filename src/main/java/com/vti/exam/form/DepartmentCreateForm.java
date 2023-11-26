package com.vti.exam.form;

import com.vti.exam.validate.DepartmentNameNotExists;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
public class DepartmentCreateForm {
    @NotBlank(message = "Tên phòng ban không được để trống")
    @DepartmentNameNotExists
    private String name;

    @NotNull(message = "Số lượng nhân viên không được để trống")
    @PositiveOrZero(message = "Số lượng nhân viên phải lớn hơn hoặc bằng 0")
    private int totalMember;

    @Pattern(
            regexp = "DEV|TEST|SCRUM_MASTER|PM",
            message = "Loại phòng ban không hợp lệ"
    )
    private String type;
    private List<AccountCreateForm> accounts;

    @Getter
    @Setter
    public static class AccountCreateForm {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String role;
        private int departmentId;
    }
}
