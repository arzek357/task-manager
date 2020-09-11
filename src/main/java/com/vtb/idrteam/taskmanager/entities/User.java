package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@ToString(of ={"id", "username", "name", "surname", "email", "roles"})
@EqualsAndHashCode(exclude = {"projects", "notifications"})
@Table(name = "users")
@NoArgsConstructor
public class User {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Id.class)
    private Long id;

    @NotEmpty
    @NotNull
    @JsonView(Views.Small.class)
    @Column(name = "username")
    private String username;

    @NotNull
    @JsonIgnore
    @Column(name = "password")
    private String password;

    @JsonView(Views.Small.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Small.class)
    @Column(name = "surname")
    private String surname;

    @JsonView(Views.FullUser.class)
    @Column(name = "email")
    private String email;

    @JsonView(Views.FullUser.class)
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonView(Views.FullUser.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullUser.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    @JsonView(Views.FullUser.class)
    @ManyToMany
    @JoinTable(name = "users_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects = new HashSet<>();

    @JsonView(Views.FullUser.class)
    @ManyToMany
    @JoinTable(name = "users_notifications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private Set<Notification> notifications = new HashSet<>();

//    @JsonView(Views.FullUser.class)
//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
//    private List<TaskParticipant> tasksParticipants = new ArrayList<>();

    public void addProject(Project project){
        projects.add(project);
        project.getUsers().add(this);
    }

    public void addRole(Role role){
        roles.add(role);
    }
}
