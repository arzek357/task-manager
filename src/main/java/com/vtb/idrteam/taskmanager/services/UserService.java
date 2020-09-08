package com.vtb.idrteam.taskmanager.services;

import com.vtb.idrteam.taskmanager.entities.Role;
import com.vtb.idrteam.taskmanager.entities.TaskParticipant;
import com.vtb.idrteam.taskmanager.entities.User;
import com.vtb.idrteam.taskmanager.exceptions.ResourceNotFoundException;
import com.vtb.idrteam.taskmanager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found, id = " + id));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

//    public List<UserDto> findByTasksParticipants(List<TaskParticipant> participants){
//        return userRepository.findAllByTasksParticipants(participants);
//    }


//    public List<Project> findProjectsByUsername(String username) {
//        List<UserProject> userProjects = userProjectRepository.findByUser(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username))));
//        return userProjects.stream().map(UserProject::getProject).collect(Collectors.toList());
//    }
//
//    public List<ProjectDto> findProjectsDtoByUsername(String username) {
//        List<UserProject> userProjects = userProjectRepository.findByUser(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username))));
//        return userProjects.stream().map(userProject -> new ProjectDto(userProject.getProject().getName(), userProject.getProject().getDescription())).collect(Collectors.toList());
//    }
}
