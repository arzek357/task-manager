package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"task"})
@EqualsAndHashCode(exclude = {"users", "task"})
@Entity
@Table(name = "notifications")
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.Small.class)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @JsonView(Views.BigNotification.class)
    @Column(name = "message", length = 1500, nullable = false)
    private String message;

    @JsonView(Views.FullNotification.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @JsonView(Views.FullNotification.class)
    @ManyToMany(mappedBy = "notifications")
    private Set<User> users = new HashSet<>();

    @JsonView(Views.BigNotification.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullNotification.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    public void addUser(User user) {
        users.add(user);
    }
}
