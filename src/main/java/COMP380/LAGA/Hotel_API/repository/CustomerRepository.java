package COMP380.LAGA.Hotel_API.repository;

import COMP380.LAGA.Hotel_API.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
