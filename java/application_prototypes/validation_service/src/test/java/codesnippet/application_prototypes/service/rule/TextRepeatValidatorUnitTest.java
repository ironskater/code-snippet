package codesnippet.application_prototypes.service.rule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextRepeatValidatorUnitTest
{
    @Autowired
    private TextRepeatValidator textRepeatValidator;

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1", "ab", "12", "a1", "1a", "a1ba1"})
    public void validate_notContainRepeatSequence_shouldReturnTrue(String text) {
        // Arrange
        // Act
        boolean actual = this.textRepeatValidator.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should not contain any sequence of characters "
                    + "immediately followed by the same sequence", text)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "abab", "11", "1212", "ab12ab12", "ab12ab123", "3ab12ab12"})
    public void validate_containRepeatSequence_shouldReturnFalse(String text) {
        // Arrange
        // Act
        boolean actual = this.textRepeatValidator.validate(text);

        // Assert
        assertThat(actual)
            .as("check the text[%s], it should contain any sequence of characters "
                    + "immediately followed by the same sequence", text)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    public void validate_paramIsNull_shouldThrowNullPointerException(String text) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> {
            this.textRepeatValidator.validate(text);
        }).isInstanceOf(NullPointerException.class);
    }
}
