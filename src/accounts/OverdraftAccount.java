package accounts;

public class OverdraftAccount extends UniversalAccount {
	double interestRate = 0.2 ;
	
	public OverdraftAccount() {
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public OverdraftAccount (double Balance){
		this.setBalance(Balance);
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public void balanceRecalculation() {
		if (getBalance() < 0) 
			setBalance(getBalance() + getBalance() * interestRate);
	}
	
	public boolean remove(double s) {
		setBalance(getBalance() - s);
		return true;
	}

}
