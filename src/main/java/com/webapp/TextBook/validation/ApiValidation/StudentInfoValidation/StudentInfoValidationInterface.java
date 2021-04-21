package com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE }) // add TYPE to be applicable to class
@Retention(RUNTIME)
@Constraint(validatedBy = StudentInfoValidationImpl.class)
@Documented
public @interface StudentInfoValidationInterface {
    /***
     * Refer to LoginUserInfoValidatorInterface for context
     *
     */
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
