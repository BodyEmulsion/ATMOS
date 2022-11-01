package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.jpa.exceptions.NotificationNotFoundException;
import com.peltikhin.atmos.jpa.models.Notification;
import com.peltikhin.atmos.jpa.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    default Notification findByIdOrError(Long id) {
        return findById(id).orElseThrow(() -> new NotificationNotFoundException(id));
    }

    Collection<Notification> findAllByTask_Project_UserAndTimeBetween(User user, Date after, Date before);
}
