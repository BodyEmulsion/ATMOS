package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
