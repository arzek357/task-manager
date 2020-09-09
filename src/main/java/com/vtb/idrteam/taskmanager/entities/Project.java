package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.simpletables.UserTaskAuthority;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Data
@Getter
@Setter
@EqualsAndHashCode(exclude = "users")
@Table(name = "projects")
@NoArgsConstructor
public class Project {
    @JsonView(Views.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonView(Views.Small.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Small.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.BigProject.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @JsonView(Views.FullProject.class)
    @CreationTimestamp
    @Column(name = "created_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullProject.class)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    @JsonView(Views.BigProject.class)
    @ManyToMany(
            mappedBy = "projects",
            cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    @JsonView(Views.BigProject.class)
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Task> tasks = new ArrayList<>();
}
