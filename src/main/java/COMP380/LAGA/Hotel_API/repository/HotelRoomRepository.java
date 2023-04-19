package COMP380.LAGA.Hotel_API.repository;

import COMP380.LAGA.Hotel_API.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * HotelRoomRepository is an interface that extends JpaRepository to provide
 * a means to access and manipulate HotelRoom entities in the database.
 *
 * <p>By extending JpaRepository, HotelRoomRepository inherits all its methods
 * to perform CRUD operations on HotelRoom entities. Additionally, JpaRepository
 * provides paging and sorting functionalities out of the box.
 *
 * <p>Spring Data JPA automatically generates an implementation of this
 * interface at runtime, so there is no need to provide an explicit
 * implementation.
 */
@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, String> {
}
