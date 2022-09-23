package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
