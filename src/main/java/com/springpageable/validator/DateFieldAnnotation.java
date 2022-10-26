package com.springpageable.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = DateFieldValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface DateFieldAnnotation {
    String message() default "To date must be max 27 days after today";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
