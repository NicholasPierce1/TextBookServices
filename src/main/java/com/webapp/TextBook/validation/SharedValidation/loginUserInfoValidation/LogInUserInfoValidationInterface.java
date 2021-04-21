package com.webapp.TextBook.validation.SharedValidation.loginUserInfoValidation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE }) // add TYPE to be applicable to class
@Retention(RUNTIME)
@Constraint(validatedBy = LoginUserInfoValidatorImpl.class)
@Documented
public @interface LogInUserInfoValidationInterface {
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


    boolean haveSuffix() default true;


}
