package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.exceptions.ResourceNotFoundException;
import COMP380.LAGA.Hotel_API.model.Customer;
import COMP380.LAGA.Hotel_API.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable(value = "email") String email)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(Long.valueOf(email))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this email :: " + email));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/customers/{email}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "email") String email,
                                                   @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(Long.valueOf(email))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this email :: " + email));

        customer.setEmail(customerDetails.getEmail());
        customer.setName(customerDetails.getName());
        customer.setNumber(customerDetails.getNumber());
        customer.setAddress(customerDetails.getAddress());
//      customer.setEmployee(customerDetails.getEmployee());

        final Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{email}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "email") String email)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(Long.valueOf(email))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this email :: " + email));

        customerRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
