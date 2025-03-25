package com.fonseca.DesafioBackEnd.controllers;

import com.fonseca.DesafioBackEnd.Service.UserService;
import com.fonseca.DesafioBackEnd.domain.user.User;
import com.fonseca.DesafioBackEnd.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/post")
    ResponseEntity<User> postUser(@RequestBody UserDTO data){
        User user = new User(data);
        this.userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
