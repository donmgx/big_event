package com.hebei.annotation;

import com.hebei.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class}) //提供校验规则
public @interface State {

    //校验失败后的信息
    String message() default "state只能为已发布或草稿";

    //指定分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};
}
