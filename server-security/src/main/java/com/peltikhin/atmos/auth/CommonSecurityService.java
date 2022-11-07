package com.peltikhin.atmos.auth;

import com.peltikhin.atmos.exceptions.NotEnoughAuthoritiesException;
import com.peltikhin.atmos.jpa.repositories.BlockRepository;
import com.peltikhin.atmos.jpa.repositories.NotificationRepository;
import com.peltikhin.atmos.jpa.repositories.ProjectRepository;
import com.peltikhin.atmos.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

//TODO: figure out something cleaver in order to reduce code duplication
@Service
public class CommonSecurityService {
    private final AuthUser authUser;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final NotificationRepository notificationRepository;
    private final BlockRepository blockRepository;

    public CommonSecurityService(AuthUser authUser, TaskRepository taskRepository, ProjectRepository projectRepository, NotificationRepository notificationRepository, BlockRepository blockRepository) {
        this.authUser = authUser;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.notificationRepository = notificationRepository;
        this.blockRepository = blockRepository;
    }

    public boolean hasTaskOwnerRights(Long id) {
        if (!taskRepository.findByIdOrError(id).getOwnerId().equals(authUser.getUser().getId()))
            throw new NotEnoughAuthoritiesException("You don't have rights to task with id = " + id.toString());
        return true;
    }

    public boolean hasNotificationOwnerRights(Long id) {
        if (!notificationRepository.findByIdOrError(id).getOwnerId().equals(authUser.getUser().getId()))
            throw new NotEnoughAuthoritiesException("You don't have rights to notification with id = " + id.toString());
        return true;
    }

    public boolean hasBlockOwnerRights(Long id) {
        if (!blockRepository.findByIdOrError(id).getOwnerId().equals(authUser.getUser().getId()))
            throw new NotEnoughAuthoritiesException("You don't have rights to block with id = " + id.toString());
        return true;
    }

    public boolean hasProjectOwnerRights(Long id) {
        if (!projectRepository.findByIdOrError(id).getOwnerId().equals(authUser.getUser().getId()))
            throw new NotEnoughAuthoritiesException("You don't have rights to project with id = " + id.toString());
        return true;
    }
}
