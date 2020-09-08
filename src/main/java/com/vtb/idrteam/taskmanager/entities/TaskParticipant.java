package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtb.idrteam.taskmanager.entities.simpletables.UserTaskAuthority;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "taskparticipants")
@NoArgsConstructor
public class TaskParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_authority_id", referencedColumnName = "id")
    private UserTaskAuthority userTaskAuthority;
}
