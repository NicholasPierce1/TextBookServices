package com.webapp.TextBook.validation.loginUserInfoValidation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;



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
public @interface  LogInUserInfoValidationInterface {
    String message() default "{org.hibernate.validator.referenceguide.chapter06.CheckCase." +
            "message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    //int value() default 22; <------ for reference
    boolean haveSuffix() default false;



//    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
//    @Retention(RUNTIME)
//    @Documented
//    @interface List {
//        com.webapp.TextBook.validation.loginUserInfoValidation[] value();
//    }
}
