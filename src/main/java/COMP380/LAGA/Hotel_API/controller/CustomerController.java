//package COMP380.LAGA.Hotel_API.controller;
//
//import COMP380.LAGA.Hotel_API.model.Customer;
//import COMP380.LAGA.Hotel_API.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/customers")
//public class CustomerController {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @CrossOrigin
//    @GetMapping
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    @CrossOrigin
//    @GetMapping("/{fullName}")
//    public ResponseEntity<Customer> getCustomerByFullName(@PathVariable("fullName") String fullName) {
//        Optional<Customer> customer = customerRepository.findById(fullName);
//
//        if (customer.isPresent()) {
//            return ResponseEntity.ok().body(customer.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @CrossOrigin
//    @PostMapping
//    public Customer createCustomer(@RequestBody Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//    @CrossOrigin
//    @PutMapping("/{fullName}")
//    public ResponseEntity<Customer> updateCustomer(@PathVariable("fullName") String fullName, @RequestBody Customer customerDetails) {
//        Optional<Customer> customer = customerRepository.findById(fullName);
//
//        if (customer.isPresent()) {
//            Customer updatedCustomer = customer.get();
//            updatedCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
//            updatedCustomer.setEmail(customerDetails.getEmail());
//            updatedCustomer.setAddress(customerDetails.getAddress());
//            updatedCustomer.setCreditCardNumber(customerDetails.getCreditCardNumber());
//
//            return ResponseEntity.ok().body(customerRepository.save(updatedCustomer));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @CrossOrigin
//    @DeleteMapping("/{fullName}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable("fullName") String fullName) {
//        Optional<Customer> customer = customerRepository.findById(fullName);
//
//        if (customer.isPresent()) {
//            customerRepository.delete(customer.get());
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
