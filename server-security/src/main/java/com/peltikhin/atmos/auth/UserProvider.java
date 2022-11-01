package com.peltikhin.atmos.auth;

import com.peltikhin.atmos.jpa.models.User;

public interface UserProvider {
    User getUser();
}
