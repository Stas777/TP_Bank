package accounts;

public class CheckingAccount extends UniversalAccount {
	int TotalTransactions;			// ���������� ����������� ���������� � ������� ������
	int MounthlyQuota;				// ���������� ���������� ����������
	double PerTransactionFee;		// ����� �� ����������� ����������
	
	public double accuralOfBalance() {
		
	}
	
	public double fee () {
		return (TotalTransactions - MounthlyQuota) * PerTransactionFee;
	}

}
