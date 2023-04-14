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

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "Employee_ID")
//	private Employee employee;

	public Customer() {
	}

	public Customer(String email, String name, String number, String address) {
		this.email = email;
		this.name = name;
		this.number = number;
		this.address = address;
//		this.employee = employee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
}
