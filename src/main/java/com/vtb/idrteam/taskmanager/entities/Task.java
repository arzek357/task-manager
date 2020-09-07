package com.vtb.idrteam.taskmanager.entities;

import com.vtb.idrteam.taskmanager.entities.bindtables.UserTask;
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
    //CREATED, IN_PROCESS, IN_REVIEW, IN_REWORK, COMPLETED, CANCELED
//    public enum Status {
//        CREATED, IN_PROCESS, IN_REVIEW, IN_REWORK, COMPLETED, CANCELED;
//    }

    //Приоритет задачи имеет 6 уровней: в планах, очень низкий, низкий, средний, высокий, очень высокий.
//    public enum Priority{
//
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "deadline_time")
    private LocalDateTime deadlineTime;

//    @Column(name = "creator_id")
//    private Long creatorId;

    @CreationTimestamp
    @Column(name = "created_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp")
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<UserTask> userTasks = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "task",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true,
//            fetch = FetchType.LAZY
//    )
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Notification> notifications = new ArrayList<>();
}
