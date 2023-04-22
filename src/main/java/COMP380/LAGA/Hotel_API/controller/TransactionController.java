package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.dto.TransactionCustomerRoomDto;
import COMP380.LAGA.Hotel_API.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

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

}
