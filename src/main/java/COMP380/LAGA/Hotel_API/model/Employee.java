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
	@Column(name = "fullname", nullable = false)
	private String fullName;

	@Column(name = "phonenumber", nullable = false)
	private int phoneNumber;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	
	public Employee() {
	}
	public Employee(String fullName, int phoneNumber, String email, String address, String password) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	// Get and set the customer's full name.
	public String getFullNamel() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	// Get and set the customer's phone number.
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// Get and set the customer's email.
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// Get and set the customer's current address.
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