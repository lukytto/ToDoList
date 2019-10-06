package ds;

import java.io.Serializable;

public class Person extends User implements Serializable {
	
	public Person(String login, String pass, String name, String surname) {
		super(login, pass);
		this.name = name;
		this.surname = surname;
	}

	private String name, surname;

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	

}
