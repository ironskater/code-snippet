package codesnippet.enumtype;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppTest
{
	@Test
	public void givenPizaOrder_whenReady_thenDeliverable()
	{
		App testPz = new App();
		testPz.setStatus(App.PizzaStatus.READY);
		assertTrue(testPz.isDeliverable());
	}
}