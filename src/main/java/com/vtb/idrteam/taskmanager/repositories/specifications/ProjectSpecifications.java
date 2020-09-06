package com.vtb.idrteam.taskmanager.repositories.specifications;

import com.vtb.idrteam.taskmanager.entities.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecifications {
    public static Specification<Project> userEquals(Long userId){
        return (Specification<Project>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userProjects"), userId);
    }
}
