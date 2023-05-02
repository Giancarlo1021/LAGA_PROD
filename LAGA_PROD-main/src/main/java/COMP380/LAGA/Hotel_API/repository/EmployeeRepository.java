package COMP380.LAGA.Hotel_API.repository;

import COMP380.LAGA.Hotel_API.dto.EmployeeHotelDto;
import COMP380.LAGA.Hotel_API.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT new COMP380.LAGA.Hotel_API.dto.EmployeeHotelDto(" +
            "e.id, e.firstName, e.lastName, e.email, e.phoneNumber, e.role, " +
            "h.name, h.address, h.phoneNumber) " +
            "FROM Employee e " +
            "JOIN e.hotel h")
    List<EmployeeHotelDto> findAllEmployeesWithHotelInfo();
}
