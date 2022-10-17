package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Project;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.services.exceptions.ProjectNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Collection<Project> findByUser(User user);

    default Project findByIdOrError(Long id) {
        return findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
