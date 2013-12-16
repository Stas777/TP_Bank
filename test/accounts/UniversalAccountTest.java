package accounts;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UniversalAccountTest {
	UniversalAccount ua;

	@Before
	public void setUp() throws Exception {
		ua = new UniversalAccount();
	}

	@Test
	public void testInvest() {
		assertEquals(0.0, ua.getBalance(), 3);
		ua.setBalance(90.0);
		ua.invest(100.0);
		assertEquals(190.0, ua.getBalance(), 3);
	}

	@Test
	public void testRemove() {
		assertEquals(0.0, ua.getBalance(), 3);
		ua.setBalance(90.0);
		assertFalse(ua.remove(100.0));
		assertEquals(90.0, ua.getBalance(), 3);
		assertTrue(ua.remove(50.0));
		assertEquals(40.0, ua.getBalance(), 3);
	}

}
