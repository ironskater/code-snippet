package codesnippet.application_prototypes.service.rule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextComponentValidatorUnitTest
{
    @Autowired
    private TextComponentValidator textComponentValidator;

    @ParameterizedTest
    @ValueSource(strings = {"1a", "a1", "1a2b", "a1b2"})
    public void validate_lowercaseLetterAndDigitOnlyWithAtLeastOneOfEach_shouldReturnTrue(String text) {
        // Arrange
        // Act
        boolean actual = this.textComponentValidator.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should consist of a mixture of lowercase letters and numerical digits only, "
                    + "with at least one of each.", text)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1", "1aB"})
    public void validate_notLowercaseLetterAndDigitOnlyWithAtLeastOneOfEach_shouldReturnFalse(String text) {
        // Arrange
        // Act
        boolean actual = this.textComponentValidator.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should not consist of a mixture of "
                    + "lowercase letters and numerical digits only, with at least one of each.", text)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    public void validate_paramIsNull_shouldThrowNullPointerException(String text) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> {
            this.textComponentValidator.validate(text);
        }).isInstanceOf(NullPointerException.class);
    }
}
