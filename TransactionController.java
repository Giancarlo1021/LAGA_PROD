package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.dto.HotelRoomDto;
import COMP380.LAGA.Hotel_API.dto.TransactionCustomerRoomDto;
import COMP380.LAGA.Hotel_API.model.Customer;
import COMP380.LAGA.Hotel_API.model.Employee;
import COMP380.LAGA.Hotel_API.model.Hotel;
import COMP380.LAGA.Hotel_API.model.HotelRoom;
import COMP380.LAGA.Hotel_API.model.Transaction;
import COMP380.LAGA.Hotel_API.controller.EmployeeController;
import COMP380.LAGA.Hotel_API.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import COMP380.LAGA.Hotel_API.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<TransactionCustomerRoomDto>> getAllTransactions() {
        List<TransactionCustomerRoomDto> transactions = transactionRepository.findAllTransactionCustomerRoomInfo();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{customerEmail}")
    public ResponseEntity<List<TransactionCustomerRoomDto>> getTransactionsByEmail(@PathVariable("customerEmail") String customerEmail) {
        List<TransactionCustomerRoomDto> transactions = transactionRepository.findByCustomerEmail(customerEmail);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
    	Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transactionRepository == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }

    @CrossOrigin
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
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
