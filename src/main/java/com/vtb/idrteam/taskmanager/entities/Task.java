package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@ToString(of = {"id", "name", "description", "deadlineTime", "createdAt", "updatedAt", "archived", "state", "project"})
@Table(name = "tasks")
@NoArgsConstructor
public class Task {
    //Задачи могут иметь статусы: создана, в работе, передана на проверку, возвращена на доработку, завершена, отменена.
    //CREATED, IN_PROGRESS, ON_REVIEW, ON_REWORK, COMPLETED, CANCELED
    @AllArgsConstructor
    @Getter
    public enum State {
        CREATED("Cоздано"),
        IN_PROGRESS("В работе"),
        IN_REVIEW("Передана на проверку"),
        IN_REWORK("Возвращена на доработку"),
        COMPLETED("Завершена"),
        CANCELED("Отменена");
        private String rus;
    }

    //Приоритет задачи имеет 6 уровней: в планах, очень низкий, низкий, средний, высокий, очень высокий.
    @AllArgsConstructor
    @Getter
    public enum Priority{
        LOWEST("Очень низкий"),
        LOW("Низкий"),
        MEDIUM("Средний"),
        HIGH("Высокий"),
        HIGHEST("Самый высокий");
        private String rus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime deadlineTime;

    @CreationTimestamp
    @JsonView(Views.FullTask.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonView(Views.FullTask.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
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
    private List<TaskParticipant> taskParticipants = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Notification> notifications = new ArrayList<>();

    @JsonView(Views.Small.class)
    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private State state;

    @JsonView(Views.BigTask.class)
    @Column(name="priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    //    @JsonView(Views.BigTask.class)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "task_state_id", referencedColumnName = "id")
//    private TaskStatus taskStatus;

//    public void addTaskParticipant(User user, TaskParticipant.Authority authority){
//        this.taskParticipants.add(new TaskParticipant(this, user, authority));
//    }

    public void addTaskParticipant(TaskParticipant taskParticipant){
//        taskParticipant.setTask(this);
        taskParticipants.add(taskParticipant);
    }
}
