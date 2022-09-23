package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
