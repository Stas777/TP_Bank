package accounts;

public class CheckingAccount extends UniversalAccount {
	int TotalTransactions;			// количество проведенных транзакций в текущем мес€це
	int MounthlyQuota;				// количество допустимых транзакций
	double PerTransactionFee;		// штраф за превышенную транзакцию
	
	public double accuralOfBalance() {
		
	}
	
	public double fee () {
		return (TotalTransactions - MounthlyQuota) * PerTransactionFee;
	}

}
