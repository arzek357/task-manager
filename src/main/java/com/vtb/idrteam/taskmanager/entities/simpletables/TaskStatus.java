package com.vtb.idrteam.taskmanager.entities.simpletables;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "taskstates")
@NoArgsConstructor
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "codename")
    private String codename;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_state_id", referencedColumnName = "id")
    private TaskStatus taskStatus;
}
