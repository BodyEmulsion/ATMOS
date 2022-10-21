package com.peltikhin.atmos.jpa.models;


/**
 * Interface for all entities that could belong to user
 */
public interface OwnerIdProvider {
    Long getOwnerId();
}
