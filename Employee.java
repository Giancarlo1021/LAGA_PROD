package COMP380.LAGA.Hotel_API.model;

import jakarta.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "Employee")
public class Employee {
	
	@Id
	@Column(name = "Customer_Email", nullable = false)
	private String email;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Number", nullable = false)
	private String number;

	@Column(name = "Address", nullable = false)
	private String address;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	
	public Employee() {
	}
	public Employee(String email, String name, String number, String address, String password) {
		this.email = email;
		this.name = name;
		this.number = number;
		this.address = address;
		this.password = password;
	}

	// Get and set the employee's email.
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// Get and set the employee's name.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// Get and set the employee's phone number.
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	// Get and set the employee's current address.
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	// Get and set the employee's password.
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}