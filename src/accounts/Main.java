package accounts;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {	
	
	static Scanner scan = new Scanner(System.in);
	static int accountNumber;
	static CredentialsList credList = new CredentialsList();
			
	public static void main(String[] args) throws IOException, IllegalArgumentException {
				
		String s = "";
		Scanner in = new Scanner(new File("data/accountnumber.txt"));
		s = in.nextLine(); 
		accountNumber = Integer.parseInt(s);
		in.close();
				
		credList.loadCredentialsFromXml();
		
		String login = "";
		UniversalAccount ua;
		
		while (true) {
			login = getLogin();	
			AccountsList accList = new AccountsList();
			ua = getAccount(login,accList);
			if (ua != null)
				workWithAccount(accList, ua);	
		} 
	} // main
	
	//--- getLogin ------------------------------------------------------------------------------------------	
	private static String getLogin() {
		String login = "", password = "", s;	
		CredentialsList cl = new CredentialsList();
		
		boolean isRepeat = true;				// условие выхода из цикла
		
		do {
			System.out.println("To login enter 1 ");
			System.out.println("To registrate enter 2 ");	
			System.out.println("To exit enter 3 ");
			s = scan.next();			
			
			switch (s) {
				case "1": System.out.print("Enter login: ");
					login = scan.next();
					System.out.print("Enter password: ");
					password = scan.next();
					if (credList.existCredentials(login, password)) {
						System.out.printf("Welcome, %s!\n\n", login);
						isRepeat = false;
					}
					else 
						System.out.printf("%s\n\n", "Your credentials do not match. Please try again.");
					break;
					
				case "2": System.out.print("Enter login: ");
					login = scan.next();
					if (credList.existLogin(login)) {
						System.out.printf("%s\n\n", "This login already exists. Please try again.");
						break;
					}
						
					System.out.print("Enter password: ");
					password = scan.next();
					
					credList.addCredentials(login, password);
					System.out.printf("%s\n\n", "You have successfully registered.");	
					isRepeat = false;
					break;
					
				case "3": System.out.println("Sistem is down.");
					credList.saveCredentialsToXml();
					System.exit(0);
					break;
					
				default: 
					System.out.print("Incorrect entering. Please try again.");
					break;				
			}
		} while (isRepeat);
		
		return login;	
		
	} // getLogin
	
	//--- getAccount ------------------------------------------------------------------------------------------	
	private static UniversalAccount getAccount(String login, AccountsList accList) {
		
		String s;
		UniversalAccount ua = new UniversalAccount();
		ua = null;
		double balance;
		
		accList.loadAccountsFromXml(login);
		
		String accountType = "";
		Boolean isRepeat = true;
		
		do {
			System.out.println("To create account enter 1 ");
			System.out.println("To work with account enter 2 ");
			System.out.println("To exit enter 3 ");
			s = scan.next();
			
			switch (s) {
				case "1": System.out.println("To create Saving Account enter 1 ");
				 	System.out.println("To create Checking Account enter 2 ");
					System.out.println("To create Overdraft Account enter 3 ");
					System.out.println("To create Timed Maturity Account enter 4 ");
					System.out.println("To exit enter 5 ");

					do {					
						s = scan.next();
						switch (s) {
							case "1": accountType = "saving";
								break;
							case "2": accountType = "checking";
								break;
							case "3": accountType = "overdraft";
								break;
							case "4": accountType = "timed";
								break;
							case "5":
								break;
							default: 
								System.out.print("Incorrect entering. Please try again.");
								break;						
						}
					} while (!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") && !s.equals("5"));
					
					if ( !s.equals("5")) {				
						if (accList.existsAccount(accountType))
							System.out.printf("%s\n\n", "You already have such account. Please create another or work with existing.");
						else {
							ua = AccountsFactory.getAccountFromFactory(accountType);
							ua.setType(accountType);
							ua.setAccountNumber(accountNumber);
							accList.addAccount(ua);
							accountNumber++;
							
							// записываю новый accountNumber в файл
							FileWriter writer;
							try {
								writer = new FileWriter(new File("data/accountnumber.txt"));
								writer.write(Integer.toString(accountNumber));
								writer.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
								
							System.out.printf("%s%s%s\n\n", "Your ", accountType, " account has been successfully created.");
							return ua;					
						}
					} else {
						System.out.println("Session completed.");
						return null;
					}						
					break;
				case "2": 
					int num;
					String acType;
					do {	
						System.out.println("Please enter Account number ");
						System.out.println("OR enter 0 to exit ");
						num = scan.nextInt();
						if (num == 0) {
							System.out.printf("Session completed.\n\n");
							return null;
						}
						ua = accList.getAccountByNumber(login, num);
						
						if (ua == null) {
							System.out.println("You do not have the Account with entered number.");
							System.out.println("Please try again.");							
						} else {
							System.out.printf("You have %s Account with Number %d.\n\n", ua.getType(), num);
							return ua;
						}
					} while(true);
				case "3": System.out.println("Session completed.");
					return null;
				default: 
					System.out.print("Incorrect entering. Please try again.");
					break;	
			}
			
		} while (isRepeat);		
		return ua;		
	} // getAccount
	
	//--- workWithAccount ------------------------------------------------------------------------------------------		
	private static void workWithAccount(AccountsList accList, UniversalAccount ua) {
		String s;
		double sum;
		AccountsList al = new AccountsList();
		
		do {
			System.out.println("To Check Balance enter 1 ");
		 	System.out.println("To Invest enter 2 ");
			System.out.println("To Remove enter 3 ");
			System.out.println("To exit enter 4 ");
			s = scan.next();
			
			switch (s) {
				case "1": System.out.printf("The Balance = %f\n\n", ua.checkBalance());
					break;
				case "2": System.out.println("Enter the sum you want to invest: ");
					sum = scan.nextFloat();
					ua.invest(sum);
					System.out.printf("Investment completed successfully. Now the Balance = %f\n\n", ua.getBalance());
					break;
				case "3": System.out.println("Enter the sum you want to remove: ");
					sum = scan.nextFloat();
					if (ua.remove(sum)) {
						System.out.printf("Removal completed successfully. Now the Balance = %f\n\n", ua.getBalance());
					} else 
						System.out.println("Removal is impossible because insufficient balance.");
					break;
				case "4": break;
				default: 
					System.out.print("Incorrect entering. Please try again.");
					break;	
			}
		} while (!s.equals("4"));
		accList.saveAccountsToXml(accList);
		System.out.printf("Session completed.\n\n");
		return;
	} // workWithAccount
	
}
