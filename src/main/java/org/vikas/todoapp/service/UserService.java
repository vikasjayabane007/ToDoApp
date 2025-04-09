package org.vikas.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vikas.todoapp.model.User;
import org.vikas.todoapp.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public String saveUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "registered";
    }

    public boolean userExists(User user) {
        return userRepo.existsUserByUsername(user.getUsername());
    }

    public String verifyUser(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(user.getUsername());
                return token;
            }
        }
        catch (Exception e) {
            return "failed";
        }
        return "failed";
    }
}
