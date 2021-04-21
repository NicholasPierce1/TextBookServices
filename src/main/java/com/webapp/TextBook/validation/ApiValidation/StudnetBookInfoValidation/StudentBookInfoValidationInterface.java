package com.webapp.TextBook.validation.ApiValidation.StudnetBookInfoValidation;

import com.webapp.TextBook.validation.ApiValidation.StudentInfoValidation.StudentInfoValidationImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Refer to LoginUserInfoValidatorInterface for context
 *
 */
@Target({ TYPE }) // add TYPE to be applicable to class
@Retention(RUNTIME)
@Constraint(validatedBy = StudentBookInfoValidationImpl.class)
@Documented
public @interface StudentBookInfoValidationInterface {
    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
