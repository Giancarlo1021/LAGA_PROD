package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.exceptions.ResourceNotFoundException;
import COMP380.LAGA.Hotel_API.model.Customer;
import COMP380.LAGA.Hotel_API.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return hotelRepository.findAll();
    }

    @GetMapping("/customers/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable(value = "email") Long email)
            throws ResourceNotFoundException {
        Customer customer = hotelRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this email :: " + email));
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return hotelRepository.save(customer);
    }

    @PutMapping("/customers/{email}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "email") Long email,
                                                   @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = hotelRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this email :: " + email));

        customer.setEmail(customerDetails.getEmail());
        customer.setName(customerDetails.getName());
        customer.setNumber(customerDetails.getNumber());
        customer.setAddress(customerDetails.getAddress());

        final Customer updatedCustomer = hotelRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{email}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "email") String email)
            throws ResourceNotFoundException {
        Customer customer = hotelRepository.findById(Long.valueOf(email))
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this email :: " + email));

        hotelRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
