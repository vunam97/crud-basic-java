package com.vti.exam.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = DepartmentNameNotExistsValidator.class)
public @interface DepartmentNameNotExists {
    String message() default "Tên phòng ban đã tồn tại";

    Class<?>[] groups() default  { };

    Class<? extends Payload>[] payload() default { };
}