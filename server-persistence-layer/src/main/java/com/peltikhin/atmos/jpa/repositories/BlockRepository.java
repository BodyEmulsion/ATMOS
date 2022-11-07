package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.exceptions.BlockNotFoundException;
import com.peltikhin.atmos.jpa.models.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
    Collection<Block> findByProject_User_Id(Long id);

    Collection<Block> findByProjectId(Long id);

    default Block findByIdOrError(Long id) {
        return findById(id).orElseThrow(() -> new BlockNotFoundException(id));
    }
}
