package codesnippet.enums;

import org.junit.jupiter.api.Test;

public class OperationTest
{
	@Test
	public void testOperation()
	{
        System.out.println(Operation.PLUS.operate(1.1, 2.0));
	}
}