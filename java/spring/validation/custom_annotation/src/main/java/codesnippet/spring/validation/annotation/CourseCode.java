package codesnippet.spring.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Constraint: Defining CourseCodeConstraintValidator.class,
 *              which is a helper class that contains business rules/validation logic
 * @Target: Can apply custom annotation to a method or field
 * @Retention: RUNTIME means keep this annotation in the compiled java bytecode,
 *              so we can use it, and introspect on it, and instrument on it during runtime
 */
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // if the caller doesn't provide a value, then we'll use the default valud: "LUV"
    public String[] value() default {"LUV", "TOPS"};

    public String message() default "must start with LUV or TOPS";

    // groups is you can actually group validation constraints together
    // {} is empty collection
    public Class<?>[] groups() default {};

    // payload is you can give additional information about the validation error
    public Class<? extends Payload>[] payload() default {};
}

