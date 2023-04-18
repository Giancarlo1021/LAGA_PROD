package COMP380.LAGA.Hotel_API.model;

import jakarta.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "Hotel_Room")
public class HotelRoom {
	
	@Id
	@Column(name = "Room_Number", nullable = false)
	private int roomNumber;
	
	@Column(name = "Room_Type", nullable = false)
	private String room;

	@Column(name = "Booking_Status", nullable = false)
	private boolean status;

	
	public HotelRoom() {
	}
	public HotelRoom(String room, int roomNumber, boolean status) {
		this.room = room;
		this.roomNumber = roomNumber;
		this.status = status;
	}

	// Get and set the booking availability of the hotel room.
	public boolean getAvailability() {
		return status;
	}
	public void setAvailability(boolean status) {
		this.status = status;
	}

	// Get and set the type of hotel room.
	public String getRoomType() {
		return room;
	}
	public void setRoomType(String room) {
		this.room = room;
	}

	// Get and set the number of the hotel room.
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
}