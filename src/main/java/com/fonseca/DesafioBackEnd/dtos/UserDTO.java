package com.fonseca.DesafioBackEnd.dtos;

import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, String email, String password, UserType userType, BigDecimal balance, Long user_id) {

    public UserDTO(User data){
        this(data.getFirstName(), data.getLastName(), data.getDocument(), data.getEmail(), data.getPassword(), data.getType(), data.getBalance(), data.getId());
    }
}
