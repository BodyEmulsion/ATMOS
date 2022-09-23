package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Settings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, Long> {
}
