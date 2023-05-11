package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.model.Customer;
import COMP380.LAGA.Hotel_API.model.Employee;
import COMP380.LAGA.Hotel_API.repository.CustomerRepository;
import COMP380.LAGA.Hotel_API.repository.EmployeeRepository;
import main.java.COMP380.LAGA.Hotel_API.controller.DeleteMapping;
import main.java.COMP380.LAGA.Hotel_API.controller.Employee;
import main.java.COMP380.LAGA.Hotel_API.controller.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @CrossOrigin
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customer.setFirstName(updatedCustomer.getFirstName()); // Corrected this line
        customer.setLastName(updatedCustomer.getLastName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id, @PathVariable Integer employeeId) {
        Customer customer = customerRepository.findById(id).orElse(null);
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (customer != null && employee != null) {
            customerRepository.delete(customer);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
