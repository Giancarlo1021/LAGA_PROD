package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.model.Hotel;
import COMP380.LAGA.Hotel_API.model.HotelRoom;
import COMP380.LAGA.Hotel_API.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return ResponseEntity.ok(hotels);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return ResponseEntity.ok(hotel.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel savedHotel = hotelRepository.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }

    @PostMapping("/{hotelId}/rooms")
    @Transactional
    public Hotel addHotelRoom(@PathVariable Long hotelId, @RequestBody HotelRoom hotelRoom) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()) {
            Hotel hotel = optionalHotel.get();
            hotelRoom.setHotel(hotel);
            Set<HotelRoom> hotelRooms = hotel.getHotelRooms();
            hotelRooms.add(hotelRoom);
            hotel.setHotelRooms(hotelRooms); // update the hotel with the new set of rooms
            hotelRepository.save(hotel);
            return hotel;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with ID " + hotelId);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Optional<Hotel> existingHotel = hotelRepository.findById(id);
        if (existingHotel.isPresent()) {
            hotel.setId(id);
            Hotel savedHotel = hotelRepository.save(hotel);
            return ResponseEntity.ok(savedHotel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            hotelRepository.delete(hotel.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
