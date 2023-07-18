package codesnippet.spring.playground;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class BigDecimalTest {

    @Test
    public void whenBigDecimalCreatedFromDouble_thenValueMayNotMatch() {

        BigDecimal bdFromDouble = new BigDecimal(0.1d);
        BigDecimal bdFromString = new BigDecimal("0.1");
        BigDecimal bdFromValueOf = BigDecimal.valueOf(0.1d);

        System.out.println(bdFromDouble.toString());
        System.out.println(bdFromString.toString());
        System.out.println(bdFromValueOf.toString());
    }
}
