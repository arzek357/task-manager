//package com.vtb.idrteam.taskmanager.entities.bindtables;
//
//import com.vtb.idrteam.taskmanager.entities.Task;
//import com.vtb.idrteam.taskmanager.entities.User;
//import com.vtb.idrteam.taskmanager.entities.simpletables.UserTaskAuthority;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Table(name = "users_tasks")
//@NoArgsConstructor
//public class UserTask {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "task_authority_id", referencedColumnName = "id")
//    private UserTaskAuthority userTaskAuthority;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Task task;
//
//}
