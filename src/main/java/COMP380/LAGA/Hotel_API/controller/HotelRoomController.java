package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.dto.HotelRoomDto;
import COMP380.LAGA.Hotel_API.model.Hotel;
import COMP380.LAGA.Hotel_API.model.HotelRoom;

import COMP380.LAGA.Hotel_API.repository.HotelRepository;
import COMP380.LAGA.Hotel_API.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hotel-rooms")
public class HotelRoomController {

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<HotelRoom>> getAllHotelRooms() {
        List<HotelRoom> hotelRooms = hotelRoomRepository.findAll();
        return new ResponseEntity<>(hotelRooms, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<HotelRoom> getHotelRoomById(@PathVariable("id") Long id) {
        Optional<HotelRoom> hotelRoom = hotelRoomRepository.findById(id);
        return hotelRoom.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin
    @GetMapping("/room_number")
    public ResponseEntity<HotelRoom> getHotelRoomByRoomNumber(@RequestParam int roomNumber) {
        Optional<HotelRoom> hotelRoomOptional = hotelRoomRepository.findByRoomNumber(roomNumber);
        if (hotelRoomOptional.isPresent()) {
            HotelRoom hotelRoom = hotelRoomOptional.get();
            return ResponseEntity.ok(hotelRoom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/available_rooms")
    public ResponseEntity<List<HotelRoom>> getAvailableHotelRooms() {
        List<HotelRoom> availableHotelRoom = hotelRoomRepository.findAllAvailableRoomsSortedByHotel();
        return new ResponseEntity<>(availableHotelRoom, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<HotelRoom> createHotelRoom(@RequestBody HotelRoom hotelRoom) {
        HotelRoom newHotelRoom = hotelRoomRepository.save(hotelRoom);
        return new ResponseEntity<>(newHotelRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelRoom> updateHotelRoom(@PathVariable("id") Long id, @RequestBody HotelRoom hotelRoom) {
        Optional<HotelRoom> existingHotelRoom = hotelRoomRepository.findById(id);
        if (existingHotelRoom.isPresent()) {
            HotelRoom updatedHotelRoom = existingHotelRoom.get();
            updatedHotelRoom.setHotel(hotelRoom.getHotel());
            updatedHotelRoom.setRoomNumber(hotelRoom.getRoomNumber());
            updatedHotelRoom.setRoomType(hotelRoom.getRoomType());
            updatedHotelRoom.setPrice(hotelRoom.getPrice());
            updatedHotelRoom.setIsAvailable(hotelRoom.getIsAvailable());
            hotelRoomRepository.save(updatedHotelRoom);
            return new ResponseEntity<>(updatedHotelRoom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotelRoom(@PathVariable("id") Long id) {
        Optional<HotelRoom> existingHotelRoom = hotelRoomRepository.findById(id);
        if (existingHotelRoom.isPresent()) {
            hotelRoomRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<HotelRoomDto>> getHotelRoomsByHotelId(@PathVariable("hotelId") Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isPresent()) {
            List<HotelRoomDto> hotelRooms = hotelRoomRepository.findHotelRoomsByHotelId(hotelId);
            return new ResponseEntity<>(hotelRooms, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
