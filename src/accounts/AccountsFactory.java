package accounts;

public class AccountsFactory {
	private enum Signs {SAVING, CHECKING, OVERDRAFT, TIMED}
	  
	public static UniversalAccount getAccountFromFactory(String Type) {
		Signs sign = Signs.valueOf(Type.toUpperCase());
	        
	    switch(sign){
	    	case SAVING  : return new SavingAccount();
	    	case CHECKING  : return new CheckingAccount();
	        case OVERDRAFT : return new OverdraftAccount();
	        case TIMED : return new TimedMaturityAccount();
	        default : throw new EnumConstantNotPresentException(Signs.class, sign.name());
	    }
	}

}
