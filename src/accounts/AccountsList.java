package accounts;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountsList {
	
	private ArrayList<UniversalAccount> AccList = new  ArrayList<UniversalAccount>();	
	private String fileName;
	
	public AccountsList() {}

	@XmlElement(name = "account")
	public ArrayList<UniversalAccount> getAccList() {
		return AccList;
	}

	public void setAccList(ArrayList<UniversalAccount> accList) {
		this.AccList = accList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public AccountsList loadAccountsFromXml(String login) {
		this.fileName = "data/" + login + ".xml";
		
		AccountsList al = new AccountsList();
		File f = new File(this.fileName);
		
		try {					
			JAXBContext jaxbContext = JAXBContext.newInstance(AccountsList.class);					
			
			if (f.exists() && f.isFile()) {
				Unmarshaller um = jaxbContext.createUnmarshaller();	
				al = (AccountsList)um.unmarshal(f);
			}			
	        return al;
		} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
	} // addAccountToXml
	
	public boolean existsAccount(String Type) {		
		for (UniversalAccount u: this.getAccList())
			if (u.getType().equals(Type))
				return true;
		return false;		
	} // existsAccount
	
	/*
	 * Если для клиента login существует аккаунт с номером Num, то возвращается указатель на него
	 * иначе возвращается null 
	 */
	public UniversalAccount getAccountByNumber(String login, int Num) {
		for (UniversalAccount u: this.getAccList())
			if (u.getAccountNumber() == Num)
				return u;
		return null;		
	} // getTypeOfAccountNumber
	
	
	
	public boolean saveAccountsToXml(AccountsList al) {
		File f = new File(fileName);
		
		try {					
			JAXBContext jaxbContext = JAXBContext.newInstance(CredentialsList.class);					
				        
	        Marshaller m = jaxbContext.createMarshaller();	        	        
	        m.marshal(al, f);
	        return true;
		} catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public void addAccount(UniversalAccount ua) {
		this.getAccList().add(ua);
	}
	
}
