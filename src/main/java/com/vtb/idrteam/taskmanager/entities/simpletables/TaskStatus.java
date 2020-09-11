package com.vtb.idrteam.taskmanager.entities.simpletables;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "taskstates")
@NoArgsConstructor
@Deprecated
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "codename")
    private String codename;
}
