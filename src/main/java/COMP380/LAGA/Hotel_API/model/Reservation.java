package COMP380.LAGA.Hotel_API.model;

import jakarta.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "Reservation")
public class Reservation {
	
	@Column(name = "startdate", nullable = false)
	private java.sql.Date startDate;

	@Column(name = "customer", nullable = false)
	private String customerName;

	@Column(name = "room", nullable = false)
	private int roomNumber;

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "enddate", nullable = false)
	private java.sql.Date endDate;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "zipcode", nullable = false)
	private int zipcode;

	@Column(name = "creditcardnumber", nullable = false)
	private int creditCardNumber;
	
	
	public Reservation() {
	}
	public Reservation(Date startDate, Date endDate, String customerName, int roomNumber, String address, 
			String city, String state, int zipcode, int creditCardNumber) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerName = customerName;
		this.roomNumber = roomNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.creditCardNumber = creditCardNumber;
	}
	public Reservation(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear, 
			String customerName, int roomNumber, String address, String city, String state, int zipcode, 
			int creditCardNumber) {
		this.startDate = createDate(startYear, startDay, startMonth);
		this.endDate = createDate(endYear, endDay, endMonth);
		this.customerName = customerName;
		this.roomNumber = roomNumber;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.creditCardNumber = creditCardNumber;
	}
	
	// Get and set the start date.
	public java.sql.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(int startDay, int startMonth, int startYear) {
		this.startDate = createDate(startYear, startDay, startMonth);
	}

	// Get and set the end date.
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(int endDay, int endMonth, int endYear) {
		this.endDate = createDate(endYear, endDay, endMonth);
	}

	// Get and set the customer's name.
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	// Get and set the room number.
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	// Get and set the billing address.
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	// Get and set the city of the billing address.
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	// Get and set the state of the billing address.
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	// Get and set the zipcode of the billing address.
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	// Get and set the credit card number to be billed.
	public int getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	// Date Creation
	public static Date createDate(int year, int day, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		
		return new java.sql.Date(calendar.getTime().getTime());
	}
}