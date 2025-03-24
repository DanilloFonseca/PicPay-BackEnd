package com.fonseca.DesafioBackEnd.Service;

import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.dtos.TransactionDTO;
import com.fonseca.DesafioBackEnd.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    public void createTransaction(TransactionDTO transaction) throws Exception {

        User sender = this.userService.findUserById(transaction.sender_id());

        this.userService.validationUserTransaction(sender, sender.getBalance());
    }
}
