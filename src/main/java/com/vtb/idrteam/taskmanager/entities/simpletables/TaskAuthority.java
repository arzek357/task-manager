package com.vtb.idrteam.taskmanager.entities.simpletables;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tasksauthorities")
@NoArgsConstructor
@Deprecated
public class TaskAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
