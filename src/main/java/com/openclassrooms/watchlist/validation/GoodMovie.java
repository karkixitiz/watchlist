package com.openclassrooms.watchlist.validation;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // this annotation is application for class type
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=GoodMovieValidator.class)

public @interface GoodMovie {
    String message() default "if a movie is as good as 8 then priority should be at least M";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default  {};

}
