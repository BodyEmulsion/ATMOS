package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Block;
import com.peltikhin.atmos.jpa.models.Project;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.services.exceptions.BlockNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
    Collection<Block> findByProject_User(User user);

    Collection<Block> findByProject(Project project);

    default Block findByIdOrError(Long id) {
        return findById(id).orElseThrow(() -> new BlockNotFoundException(id));
    }
}
