package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.FullNotification.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonView(Views.FullNotification.class)
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @JsonView(Views.Small.class)
    @Column(name = "title", length = 100)
    private String title;

    @JsonView(Views.BigNotification.class)
    @Column(name = "message", length = 1500)
    private String message;

    @JsonView(Views.FullNotification.class)
    @CreationTimestamp
    @Column(name = "created_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @JsonView(Views.FullNotification.class)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;
}
