package com.vtb.idrteam.taskmanager.entities.bindtables;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_projects")
@NoArgsConstructor
@Data
@Deprecated
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Project project;
//
//    @Column(name = "is_creator")
//    private Boolean isCreator;

//    public UserProject(User user, Project project, Boolean isCreator) {
//        this.user = user;
//        this.project = project;
//        this.isCreator = isCreator;
//    }
//
//    public UserProject(User user, Project project) {
//        this.user = user;
//        this.project = project;
//    }
}
