package accounts;
import java.util.Date;

public class SavingAccount extends UniversalAccount {
	double interestRate;
	Date ReturnDate;	
	
	public double accuralOfIR() {
		return Balance + Balance * interestRate;		
	}

}
