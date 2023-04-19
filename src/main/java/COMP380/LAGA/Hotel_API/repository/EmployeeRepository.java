package COMP380.LAGA.Hotel_API.repository;

import COMP380.LAGA.Hotel_API.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EmployeeRepository is an interface that extends JpaRepository to provide
 * a means to access and manipulate Employee entities in the database.
 *
 * <p>By extending JpaRepository, EmployeeRepository inherits all its methods
 * to perform CRUD operations on Employee entities. Additionally, JpaRepository
 * provides paging and sorting functionalities out of the box.
 *
 * <p>Spring Data JPA automatically generates an implementation of this
 * interface at runtime, so there is no need to provide an explicit
 * implementation.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
