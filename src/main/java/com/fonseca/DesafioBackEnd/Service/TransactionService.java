package com.fonseca.DesafioBackEnd.Service;

import com.fonseca.DesafioBackEnd.domain.transaction.Transaction;
import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.dtos.TransactionDTO;
import com.fonseca.DesafioBackEnd.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {

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

    }


    public boolean transactionValidation() {
        ResponseEntity<Map> response = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            Map<String, Object> responseBody = response.getBody();

            if(responseBody != null){
                String transferStatus = responseBody.get("status").toString();
                Boolean transferBooleanAuthorization = (Boolean) responseBody.get("authorization");

                return "sucess".equals(transferStatus) && Boolean.TRUE.equals(transferBooleanAuthorization);
            }
        }
        return false;
    }


}

