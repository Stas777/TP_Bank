package accounts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SavingAccountTest.class, UniversalAccountTest.class })
public class AllTests {

}
