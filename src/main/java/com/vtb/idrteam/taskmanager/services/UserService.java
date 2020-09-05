package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//    }


}
