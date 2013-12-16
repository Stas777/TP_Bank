package accounts;
import java.util.Date;

public class SavingAccount extends UniversalAccount {
	double interestRate = 0.02;
	
	public SavingAccount () {	
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public SavingAccount (double Balance){
		this.setBalance(Balance);
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public void balanceRecalculation() {
		setBalance(getBalance() + getBalance() * interestRate);		
	}

}
