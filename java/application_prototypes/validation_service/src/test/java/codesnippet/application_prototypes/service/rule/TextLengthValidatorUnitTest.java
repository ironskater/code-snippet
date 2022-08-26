package codesnippet.application_prototypes.service.rule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TextLengthValidatorUnitTest
{
    @Autowired
    private TextLengthValidator textLengthValidator;

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 12})
    public void validate_textLengthBetween5And12_shouldReturnTrue(int len) {
        // Arrange
        String arg = "x".repeat(len);

        // Act
        boolean actual = this.textLengthValidator.validate(arg);

        // Assert
        assertThat(actual)
            .as("check text len[%d], which should between 5 and 12", len)
            .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 13})
    public void validate_textLengthNotBetween5And12_shouldReturnFalse(int len) {
        // Arrange
        String arg = "x".repeat(len);

        // Act
        boolean actual = this.textLengthValidator.validate(arg);

        // Assert
        assertThat(actual)
            .as("check text len[%d], which should not between 5 and 12", len)
            .isFalse();
    }

    @ParameterizedTest
    @NullSource
    public void validate_paramIsNull_shouldThrowNullPointerException(String text) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> {
            this.textLengthValidator.validate(text);
        }).isInstanceOf(NullPointerException.class);
    }
}
