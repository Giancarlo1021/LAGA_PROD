//package COMP380.LAGA.Hotel_API.model;
//
//import jakarta.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Id;
//import javax.persistence.Column;
//
//
//@Entity
//@Table(name = "Hotel_Room")
//public class HotelRoom {
//
//	@Id
//	@Column(name = "roomnumber", nullable = false)
//	private int roomNumber;
//
//	@Column(name = "room", nullable = false)
//	private String room;
//
//	@Column(name = "status", nullable = false)
//	private boolean status;
//
//
//	public HotelRoom() {
//	}
//	public HotelRoom(int roomNumber, String room, boolean status) {
//		this.roomNumber = roomNumber;
//		this.room = room;
//		this.status = status;
//	}
//
//	// Get and set the number of the hotel room.
//	public int getRoomNumber() {
//		return roomNumber;
//	}
//	public void setRoomNumber(int roomNumber) {
//		this.roomNumber = roomNumber;
//	}
//
//	// Get and set the type of hotel room.
//	public String getRoomType() {
//		return room;
//	}
//	public void setRoomType(String room) {
//		this.room = room;
//	}
//
//	// Get and set the booking availability of the hotel room.
//	public boolean getAvailability() {
//		return status;
//	}
//	public void setAvailability(boolean status) {
//		this.status = status;
//	}
//}