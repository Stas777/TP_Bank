package accounts;
import java.util.Date;

public class TimedMaturityAccount extends SavingAccount {

		double Penalty = 0.05;
		Date ReturnDate;			// дата возврата вклада
		
		public TimedMaturityAccount() {
			this.setCreateData(java.util.Calendar.getInstance().getTime());
		}
		
		public TimedMaturityAccount (double Balance, Date ReturnDate){
			this.setBalance(Balance);
			this.setCreateData(java.util.Calendar.getInstance().getTime());
			this.ReturnDate = ReturnDate;
		}		
		
		public double receivedSumma(double s) {
			if (new Date().getTime() - ReturnDate.getTime() >= 0) {
				return s;
			}
			else 
				return s - s * Penalty;
		}
		
}
