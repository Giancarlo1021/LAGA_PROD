package COMP380.LAGA.Hotel_API.repository;

import COMP380.LAGA.Hotel_API.dto.HotelRoomDto;
import COMP380.LAGA.Hotel_API.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

    @Query("SELECT new COMP380.LAGA.Hotel_API.dto.HotelRoomDto(hr.id, hr.roomNumber, hr.roomType, " +
            "hr.price, hr.isAvailable, h.name, h.address, h.phoneNumber) " +
            "FROM HotelRoom hr " +
            "JOIN hr.hotel h " +
            "WHERE h.id = :hotelId")
    List<HotelRoomDto> findHotelRoomsByHotelId(Long hotelId);

    @Query("SELECT hr FROM HotelRoom hr WHERE hr.isAvailable = true ORDER BY hr.hotel.id")
    List<HotelRoom> findAllAvailableRoomsSortedByHotel();

    Optional<HotelRoom> findByRoomNumber(int roomNumber);
}
