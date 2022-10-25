package com.peltikhin.atmos.services;

import com.peltikhin.atmos.jpa.models.OwnerIdProvider;
import com.peltikhin.atmos.jpa.models.User;
import com.peltikhin.atmos.services.exceptions.NotEnoughAuthoritiesException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private final AuthService authService;

    public ValidationService(AuthService authService) {
        this.authService = authService;
    }

    private static boolean isTaskBelongToUser(OwnerIdProvider entity, User user) {
        return entity.getOwnerId().equals(user.getId());
    }

    public void validateUserAuthority(OwnerIdProvider entity) {
        var user = authService.getCurrentUser();
        //TODO specify entity type in exception in order to geting more understandable messages
        if (!isTaskBelongToUser(entity, user))
            throw new NotEnoughAuthoritiesException("Entity doesn't belong to user");
    }
}
