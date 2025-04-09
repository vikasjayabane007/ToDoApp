package org.vikas.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vikas.todoapp.model.User;
import org.vikas.todoapp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String test(){
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        if(userService.userExists(user))
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);

        String mess=userService.saveUser(user);
        return new ResponseEntity<>(mess, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        if(!userService.userExists(user)){
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }

        String message = userService.verifyUser(user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}


