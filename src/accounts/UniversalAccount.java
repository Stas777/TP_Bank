package accounts;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class UniversalAccount {
	private int accountNumber;
	private String Type;
	private double Balance;
	private Date CreateData;

	
	public UniversalAccount() {
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public UniversalAccount(int accountNumber, double balance, String type) {
		this.setAccountNumber(accountNumber);
		this.setBalance(balance);
		this.setType(type);
		this.setCreateData(java.util.Calendar.getInstance().getTime());
	}
	
	public void invest(double s) {
		setBalance(getBalance() + s);
	}
	
	public boolean remove(double s) {
		if (getBalance() >= s) {
			setBalance(getBalance() - s);
			return true;
		}
		else
			return false;			
	}
	
	public double checkBalance() {
		return getBalance();
	}
	
	public void balanceRecalculation() {};
	
	public void recalculateBalance() {
		if (java.util.Calendar.getInstance().getTime().getDay() == this.getCreateData().getDay()) {
			this.balanceRecalculation();
		}
	}

	@XmlElement
	int getAccountNumber() {
		return accountNumber;
	}

	void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@XmlElement
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@XmlElement
	Date getCreateData() {
		return CreateData;
	}

	void setCreateData(Date createData) {
		CreateData = createData;
	}

	@XmlElement
	double getBalance() {
		return Balance;
	}

	void setBalance(double balance) {
		Balance = balance;
	}
	
}
