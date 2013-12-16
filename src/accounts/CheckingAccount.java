package accounts;

public class CheckingAccount extends UniversalAccount {
	private int MounthlyQuota = 5;			// количество допустимых транзакций
	private double PerTransactionFee = 1;		// штраф за превышенную транзакцию
	
	public CheckingAccount() {
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public CheckingAccount (double Balance){
		this.setBalance(Balance);
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}	

	public void invest(double s) {
		setBalance(getBalance() + s);
		setMounthlyQuota(getMounthlyQuota() - 1);
	}
	
	public boolean remove(double s) {
		if (getBalance() >= s) {
			setBalance(getBalance() - s);
			setMounthlyQuota(getMounthlyQuota() - 1);
			return true;	
		}
		else
			return false;			
	}
	
	public double checkBalance() {
		setMounthlyQuota(getMounthlyQuota() - 1);
		return getBalance();		
	}
	
	public void balanceRecalculation() {
		if (getMounthlyQuota() < 0) {
			setBalance(getBalance() + getMounthlyQuota() * getPerTransactionFee());
			this.setMounthlyQuota(5);
		}
	}

	int getMounthlyQuota() {
		return MounthlyQuota;
	}

	void setMounthlyQuota(int mounthlyQuota) {
		MounthlyQuota = mounthlyQuota;
	}

	double getPerTransactionFee() {
		return PerTransactionFee;
	}

	void setPerTransactionFee(double perTransactionFee) {
		PerTransactionFee = perTransactionFee;
	}

}
