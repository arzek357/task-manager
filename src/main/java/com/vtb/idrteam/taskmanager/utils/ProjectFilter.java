package com.vtb.idrteam.taskmanager.utils;

import com.vtb.idrteam.taskmanager.entities.Project;
import com.vtb.idrteam.taskmanager.repositories.specifications.ProjectSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

@Getter
public class ProjectFilter {
    private Specification<Project> spec;

    public ProjectFilter(MultiValueMap<String, String> params){
        spec = Specification.where(null);

        if (params.containsKey("user_id") && !params.get("user_id").get(0).isEmpty()){
            long userId = Long.parseLong(params.get("user_id").get(0));
            spec.and(ProjectSpecifications.userEquals(userId));
        }
    }
}
