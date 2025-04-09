package org.vikas.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vikas.todoapp.model.User;
import org.vikas.todoapp.model.UserDetailsImpl;
import org.vikas.todoapp.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if(user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(user);
    }
}
