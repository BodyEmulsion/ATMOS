package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.exceptions.TaskNotFoundException;
import com.peltikhin.atmos.jpa.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    default Task findByIdOrError(Long id) {
        return findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    Collection<Task> findByProjectId(Long id);
}
