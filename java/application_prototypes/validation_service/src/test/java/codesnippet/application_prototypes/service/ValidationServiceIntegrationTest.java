package codesnippet.application_prototypes.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationServiceIntegrationTest
{
    @Autowired
    private ValidationService validationService;

    @ParameterizedTest
    @ValueSource(strings = {"1a2b3", "1a2b3c4d5e6f"})
    public void validate_passAllRules_shouldReturnTrue(String text) {
        // Arrange
        // Act
        boolean actual = this.validationService.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should pass the all rules:\n"
                    + "* mixture of lowercase letters and numerical digits only, with at least one of each\n"
                    + "* between 5 and 12 characters in length\n"
                    + "* not contain any sequence of characters immediately followed by the same sequence\n", text)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcde", "12345", "abcdefghijkl", "123456789012", "1a2B3"})
    public void validate_onlyNotPassTextComponentRule_shouldReturnFalse(String text) {
        // Arrange
        // Act
        boolean actual = this.validationService.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should not pass the rule:\n"
                    + "* mixture of lowercase letters and numerical digits only, with at least one of each\n", text)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1a2b", "1a2b3c4d5e6f7"})
    public void validate_onlyNotPassTextLengthRule_shouldReturnFalse(String text) {
        // Arrange
        // Act
        boolean actual = this.validationService.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should not pass the rule:\n"
                    + "* between 5 and 12 characters in length\n", text)
            .isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1a1a3", "1a2b3c1a2b3c"})
    public void validate_onlyNotPassTextRepeatRule_shouldReturnFalse(String text) {
        // Arrange
        // Act
        boolean actual = this.validationService.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should not pass the rule:\n"
                    + "* not contain any sequence of characters immediately followed by the same sequence\n", text)
            .isFalse();
    }
}
