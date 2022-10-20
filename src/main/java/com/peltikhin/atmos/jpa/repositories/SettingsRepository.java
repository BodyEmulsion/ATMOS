package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Settings;
import com.peltikhin.atmos.jpa.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, Long> {
    Optional<Settings> findByUser(User user);
}
