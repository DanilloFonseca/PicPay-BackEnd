package com.fonseca.DesafioBackEnd.service;

import com.fonseca.DesafioBackEnd.domain.transaction.Transaction;
import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.dtos.TransactionDTO;
import com.fonseca.DesafioBackEnd.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception {

        User sender = this.userService.findUserById(transaction.sender_id());
        User receiver = this.userService.findUserById(transaction.receiver_id());

        this.userService.validationUserTransaction(sender, sender.getBalance());

        if(!transactionValidation()){
            throw new Exception("Transaction not authorized");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        receiver.setBalance(receiver.getBalance().add(transaction.amount()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        //this.notificationService.notificationSender("Successful transaction!", sender);
        //this.notificationService.notificationSender("Transaction received with success!", receiver);

        return newTransaction;
    }


    public boolean transactionValidation() {
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            Map<String, Object> responseBody = response.getBody();

            if(responseBody != null){
                String transferStatus = responseBody.get("status").toString();
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if(data != null){
                    Boolean authorization = (Boolean) data.get("authorization");
                    return "success".equals(transferStatus) && Boolean.TRUE.equals(authorization);
                }
            }
        }
        return false;
    }

    public List<TransactionDTO> getAllTransactions(){
        return this.repository.findAll().stream().map(TransactionDTO::new).toList();
    }


}

