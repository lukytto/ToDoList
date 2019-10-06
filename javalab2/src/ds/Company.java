package ds;

import java.io.Serializable;

public class Company extends User implements Serializable {
	private String title;

	public Company(String login, String pass, String title) {
		super(login, pass);
		this.title = title;
	}

	@Override
	public String toString() {
		return "Company [title=" + title + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
