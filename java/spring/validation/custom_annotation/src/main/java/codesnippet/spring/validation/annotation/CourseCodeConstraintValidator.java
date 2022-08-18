package codesnippet.spring.validation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator
// the generic<A, T> is <our annotation, type of data to validate against>
    implements ConstraintValidator<CourseCode, String>
{
    private String[] coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    /**
     * value: it's the HTML form data entered by the user
     * context: helper class for additional error messages
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = false;

        if(value != null) {
            for(String prefix : this.coursePrefix) {
                result = value.startsWith(prefix);
                if(result) {
                    break;
                }
            }
        } else {
            result = true;
        }

        return result;
    }
}
