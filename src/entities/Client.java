package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Client {
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String date;
	
	private String name;
	private String email;
	private LocalDate birthDate;

	public String getName() {
		return name;
	}

	public Client(String name, String email, String birthDate) {
		this.name = name;
		this.email = email;
		this.birthDate = LocalDate.parse(birthDate, fmt);
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return name+" ("+birthDate+") - "+email;
	}
	
	
}
