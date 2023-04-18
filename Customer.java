package COMP380.LAGA.Hotel_API.model;

import jakarta.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "Customer")
public class Customer {
	
	@Id
	@Column(name = "Customer_Email", nullable = false)
	private String email;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Number", nullable = false)
	private String number;

	@Column(name = "Address", nullable = false)
	private String address;

	
	public Customer() {
	}
	public Customer(String email, String name, String number, String address) {
		this.email = email;
		this.name = name;
		this.number = number;
		this.address = address;
	}

	// Get and set the customer's email.
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// Get and set the customer's name.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// Get and set the customer's phone number.
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	// Get and set the customer's current address.
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}