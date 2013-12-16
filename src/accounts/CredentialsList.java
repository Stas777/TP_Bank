package accounts;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class CredentialsList {
	
	@XmlElement(name = "credentials")
	private ArrayList<Credentials> CredList = new  ArrayList<Credentials>();	
	private String fileName = "data/credentials.xml";

	public CredentialsList() {	
	}
	
	public CredentialsList(ArrayList<Credentials> CredList) {
		this.CredList = CredList;
	}
	
	ArrayList<Credentials> getCredList() {
		return CredList;
	}

	void setCredList(ArrayList <Credentials> credList) {
		CredList = credList;
	}
	
	public CredentialsList loadCredentialsFromXml() {
		
		CredentialsList cl = new CredentialsList();
		File f = new File(fileName);
		
		try {					
			JAXBContext jaxbContext = JAXBContext.newInstance(CredentialsList.class);					
			
			if (f.exists() && f.isFile()) {
				Unmarshaller um = jaxbContext.createUnmarshaller();	
				cl = (CredentialsList)um.unmarshal(f);
			}
	        return cl;
		} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public boolean existCredentials(String login, String password) {
		for (Credentials c: this.getCredList())
			if (c.getLogin().equals(login) && c.getPassword().equals(password))
				return true;
		return false;		
	}
	
	public boolean existLogin(String login) {
		for (Credentials c: this.getCredList())
			if (c.getLogin().equals(login))
				return true;
		return false;		
	}
	
	public boolean saveCredentialsToXml() {
		File f = new File(fileName);
		
		try {					
			JAXBContext jaxbContext = JAXBContext.newInstance(CredentialsList.class);					
				        
	        Marshaller m = jaxbContext.createMarshaller();	        	        
	        m.marshal(this, f);
	        return true;
		} catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public void addCredentials(String login, String password) {
		this.getCredList().add(new Credentials(login, password));
	}
	
}
