package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Role;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.entities.dtos.UserDto;
import com.vtb.idrteam.taskmanager.exceptions.UserCreationException;
import com.vtb.idrteam.taskmanager.repositories.RoleRepository;
import com.vtb.idrteam.taskmanager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User createUser(UserDto userDto) {
        log.debug(userDto.toString());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserCreationException("User with this username already exists");
        }

        if(userDto.getPassword() == null || userDto.getPasswordConfirm() == null){
            throw new UserCreationException("Enter both passwords");
        }

        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            throw new UserCreationException("Passwords doesnt match");
        }

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        newUser.setName(userDto.getName());
        newUser.setSurname(userDto.getSurname());
        newUser.setEmail(userDto.getEmail());
        newUser.addRole(roleRepository.findByName("ROLE_USER"));

        return saveOrUpdate(newUser);
    }
}
