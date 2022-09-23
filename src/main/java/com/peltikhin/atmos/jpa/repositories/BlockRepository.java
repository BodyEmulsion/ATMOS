package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
}
