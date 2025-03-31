package com.fonseca.DesafioBackEnd.controllers;

import com.fonseca.DesafioBackEnd.service.TransactionService;
import com.fonseca.DesafioBackEnd.domain.transaction.Transaction;
import com.fonseca.DesafioBackEnd.dtos.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/post")
    ResponseEntity<Transaction> postTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }


    @GetMapping("/get")
    ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        List<TransactionDTO> transactionDTOS = this.transactionService.getAllTransactions();
        return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
    }

}
