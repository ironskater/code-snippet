package codesnippet.application_prototypes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import codesnippet.application_prototypes.service.rule.TextComponentValidator;
import codesnippet.application_prototypes.service.rule.Validatable;

@ExtendWith(SpringExtension.class)
public class ValidationServiceUnitTest
{
    private static final String DONT_CARE = "dont_care";

    @TestConfiguration
    static class ValidationServiceUnitTestConfiguration {
        @Bean
        public Validatable validatorOne() {
            return Mockito.mock(TextComponentValidator.class);
        }

        @Bean
        public Validatable validatorTwo() {
            return Mockito.mock(TextComponentValidator.class);
        }

        @Bean
        public ValidationService validationService(List<Validatable> validatorList) {
            return new ValidationService(validatorList);
        }
    }

    @Autowired
    private Validatable validatorOne;

    @Autowired
    private Validatable validatorTwo;

    @Autowired
    private ValidationService validationService;

    @Test
    public void validate_passAllRules_shouldReturnTrue() {
        // Arrange
        when(this.validatorOne.validate(DONT_CARE)).thenReturn(true);
        when(this.validatorTwo.validate(DONT_CARE)).thenReturn(true);

        // Act
        boolean actual = this.validationService.validate(DONT_CARE);

        // Assert
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
        "true, false",
        "false, true"
    })
    public void validate_notPassOneOfRules_shouldReturnFalse(boolean param1, boolean param2) {
        // Arrange
        when(this.validatorOne.validate(DONT_CARE)).thenReturn(param1);
        when(this.validatorTwo.validate(DONT_CARE)).thenReturn(param2);

        // Act
        boolean actual = this.validationService.validate(DONT_CARE);

        // Assert
        assertThat(actual).isFalse();
    }
}
