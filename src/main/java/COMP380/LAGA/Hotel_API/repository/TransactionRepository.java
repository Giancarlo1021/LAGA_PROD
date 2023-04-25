package COMP380.LAGA.Hotel_API.repository;

import COMP380.LAGA.Hotel_API.dto.TransactionCustomerRoomDto;
import COMP380.LAGA.Hotel_API.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Changed "Long" to "Integer".

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT new COMP380.LAGA.Hotel_API.dto.TransactionCustomerRoomDto(" +
            "t.id, t.checkInDate, t.checkOutDate, t.totalAmount, " +
            "c.firstName, c.lastName, c.email, c.phoneNumber, " +
            "hr.roomNumber, hr.roomType, hr.price, hr.isAvailable) " +
            "FROM Transaction t " +
            "JOIN t.customer c " +
            "JOIN t.hotelRoom hr")
    List<TransactionCustomerRoomDto> findAllTransactionCustomerRoomInfo();

    @Query("SELECT new COMP380.LAGA.Hotel_API.dto.TransactionCustomerRoomDto(" +
            "t.id, t.checkInDate, t.checkOutDate, t.totalAmount, " +
            "c.firstName, c.lastName, c.email, c.phoneNumber, " +
            "hr.roomNumber, hr.roomType, hr.price, hr.isAvailable) " +
            "FROM Transaction t " +
            "JOIN t.customer c " +
            "JOIN t.hotelRoom hr " +
            "WHERE c.email = :email")
    List<TransactionCustomerRoomDto> findByCustomerEmail(@Param("email") String customerEmail);

}
