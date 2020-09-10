package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.simpletables.TaskAuthority;
import com.vtb.idrteam.taskmanager.entities.simpletables.TaskStatus;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
@NoArgsConstructor
public class Task {
    //Задачи могут иметь статусы: создана, в работе, передана на проверку, возвращена на доработку, завершена, отменена.
    //CREATED, IN_PROGRESS, ON_REVIEW, ON_REWORK, COMPLETED, CANCELED
//    public enum Status {
//        CREATED, IN_PROCESS, IN_REVIEW, IN_REWORK, COMPLETED, CANCELED;
//    }

    //Приоритет задачи имеет 6 уровней: в планах, очень низкий, низкий, средний, высокий, очень высокий.
//    public enum Priority{
//
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    @JsonView(Views.Small.class)
    private String name;

    @Column(name = "description", length = 500)
    @JsonView(Views.Small.class)
    private String description;

    @Column(name = "archived")
    @JsonView(Views.Small.class)
    private Boolean archived;

    @Column(name = "deadline_time")
    @JsonView(Views.BigTask.class)
    private LocalDateTime deadlineTime;

//    @Column(name = "creator_id")
//    private Long creatorId;
    @CreationTimestamp
    @JsonView(Views.FullTask.class)
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonView(Views.FullTask.class)
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    //Настройка видимости задачи
//    @JsonView(Views.BigTask.class)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "task_authority_id", referencedColumnName = "id")
//    private TaskAuthority taskAuthority;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonView(Views.BigTask.class)
    @OneToMany(mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<TaskParticipant> taskParticipants;

    @JsonIgnore
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Notification> notifications = new ArrayList<>();

    @JsonView(Views.BigTask.class)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_state_id", referencedColumnName = "id")
    private TaskStatus taskStatus;
}
