package com.vtb.idrteam.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.vtb.idrteam.taskmanager.utils.Views;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @JsonView(Views.Small.class)
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonView(Views.Small.class)
    @Column(name="authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public TaskParticipant(User user, Authority authority) {
        this.user = user;
        this.authority = authority;
    }

    //    @JsonView(Views.Small.class)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "task_authority_id", referencedColumnName = "id")
//    private TaskAuthority taskAuthority;
}
