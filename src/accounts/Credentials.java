package accounts;
import javax.xml.bind.annotation.*;

public class Credentials {
	private String Login;
	private String Password;	
	
	public Credentials () {}
	
	public Credentials (String log, String pass) {
		this.setLogin(log);
		this.setPassword(pass);
	}

	@XmlElement
	String getLogin() {
		return Login;
	}

	void setLogin(String login) {
		Login = login;
	}

	@XmlElement
	String getPassword() {
		return Password;
	}

	void setPassword(String password) {
		Password = password;
	}
}
