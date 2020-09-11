package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "users")
@ToString(of = {"id", "name", "description", "createdAt", "updatedAt", "creator"})
@Table(name = "projects")
@NoArgsConstructor
public class Project {
//    @NotNull
    @JsonView(Views.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @NotNull
    @JsonView(Views.Small.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Small.class)
    @Column(name = "description")
    private String description;

//    @NotNull
    @JsonView(Views.BigProject.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @JsonView(Views.FullProject.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullProject.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
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

    public void addTask(Task task){
        tasks.add(task);
        task.setProject(this);
    }
}
