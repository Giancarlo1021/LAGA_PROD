package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.dto.CreateTransactionRequestDto;
import COMP380.LAGA.Hotel_API.dto.TransactionCustomerRoomDto;
import COMP380.LAGA.Hotel_API.model.*;
import COMP380.LAGA.Hotel_API.repository.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

    @Autowired
    private HotelRepository hotelRepository;


    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<TransactionCustomerRoomDto>> getAllTransactions() {
        List<TransactionCustomerRoomDto> transactions = transactionRepository.findAllTransactionCustomerRoomInfo();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/customer-room-info")
    public List<TransactionCustomerRoomDto> getAllTransactionCustomerRoomInfo() {
        return transactionRepository.findAllTransactionCustomerRoomInfo();
    }

    @CrossOrigin
    @GetMapping("/get-by-email/{customerEmail}")
    public ResponseEntity<List<TransactionCustomerRoomDto>> getTransactionsByEmail(@PathVariable("customerEmail") String customerEmail) {
        List<TransactionCustomerRoomDto> transactions = transactionRepository.findByCustomerEmail(customerEmail);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/get-by-transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Transaction transaction = transactionRepository.findByIdWithCustomer(id);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @CrossOrigin
    @PostMapping("/hotels/{hotelId}/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionRequestDto request) {
        Customer customer;
        Optional<Customer> customerOptional = customerRepository.findByEmail(request.getCustomer().getEmail());
        Optional<HotelRoom> hotelRoomOptional = hotelRoomRepository.findById(request.getHotelRoomId());

        if (customerOptional.isPresent()) {
            customer = customerOptional.get();
            System.out.println("Customer found: " + customer);
        } else {
            customer = customerRepository.save(request.getCustomer());
            System.out.println("New customer created: " + customer);
        }

        if (hotelRoomOptional.isPresent()) {
            HotelRoom hotelRoom = hotelRoomOptional.get();

            Transaction transaction = new Transaction();
            transaction.setCustomer(customer);
            transaction.setHotelRoom(hotelRoom);
            transaction.setCheckInDate(request.getCheckInDate());
            transaction.setCheckOutDate(request.getCheckOutDate());
            transaction.setTotalAmount(request.getTotalAmount());

            Transaction savedTransaction = transactionRepository.save(transaction);
            System.out.println("New transaction created: " + savedTransaction);
            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } else {
            System.out.println("Hotel room not found.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction updatedTransaction = existingTransaction.get();
            updatedTransaction.setCustomer(transaction.getCustomer());
            updatedTransaction.setHotelRoom(transaction.getHotelRoom());
            updatedTransaction.setCheckInDate(transaction.getCheckInDate());
            updatedTransaction.setCheckOutDate(transaction.getCheckOutDate());
            updatedTransaction.setTotalAmount(transaction.getTotalAmount());
            transactionRepository.save(updatedTransaction);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer transactionId, @PathVariable Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee != null) {
            Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
            if (transaction == null) {
                return ResponseEntity.notFound().build();
            }
            transactionRepository.delete(transaction);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
