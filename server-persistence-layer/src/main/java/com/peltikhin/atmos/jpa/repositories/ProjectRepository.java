package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.exceptions.ProjectNotFoundException;
import com.peltikhin.atmos.jpa.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Collection<Project> findByUserId(Long id);

    default Project findByIdOrError(Long id) {
        return findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
