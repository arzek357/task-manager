package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.entities.simpletables.TaskAuthority;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "taskparticipants")
@NoArgsConstructor
public class TaskParticipant {

    @AllArgsConstructor
    @Getter
    public enum Authority{
        CREATOR,
        PERFORMER,
        SUBSCRIBER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Task task;

    @JsonView(Views.Small.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonView(Views.BigTask.class)
    @Column(name="authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

//    @JsonView(Views.Small.class)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "task_authority_id", referencedColumnName = "id")
//    private TaskAuthority taskAuthority;
}
