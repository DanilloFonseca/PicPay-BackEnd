package com.fonseca.DesafioBackEnd.Service;

import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.domain.user.UserType;
import com.fonseca.DesafioBackEnd.dtos.UserDTO;
import com.fonseca.DesafioBackEnd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validationUserTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getType() != UserType.COMMON){
            throw new Exception("User type not allowed to do a transfer");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("User has not enough money to complete the transaction");
        }

    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(
                () -> new Exception("User not found")
                );
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(UserDTO data){
        User user = new User(data);
        this.saveUser(user);
        return user;
    }

    public List<UserDTO> getAllUsers(){
        return this.repository.findAll().stream().map(UserDTO::new).toList();
    }
}
