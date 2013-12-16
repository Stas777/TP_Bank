package accounts;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SavingAccountTest {
	SavingAccount sa;

	@Before
	public void setUp() throws Exception {
		sa = new SavingAccount();
	}

	@Test
	public void testBalanceRecalculation() {
		sa.balanceRecalculation();
		assertEquals(0.0, sa.getBalance(), 3);
		sa.setBalance(100.0);
		assertEquals(102, sa.getBalance(), 3);	
	}

}
