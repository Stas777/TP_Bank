package accounts;

public class OverdraftAccount extends UniversalAccount {
	double interestRate;
	
	public double accuralOfBalance() {
		if (Balance < 0) 
			return Balance * interestRate;
		else 
			return Balance;
	}

}
