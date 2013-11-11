package accounts;
import java.util.Date;

public abstract class UniversalAccount {
	Date CreateData;
	Date CheckData;
	double Balance;
	String Login;
	String Password;
	
	public void invest(double s) {
		Balance += s;
	}
	
	public boolean remove(double s) {
		if (Balance >= s) {
			Balance -= s;
			return true;
		}
		else
			return false;			
	}
	
	public abstract double checkBalance();
	
	public boolean checkCredentials(String Login, String Password) {
		if(this.Login.equals(Login) && this.Password.equals(Password))
			return true;
		else
			return false;
	}

}
