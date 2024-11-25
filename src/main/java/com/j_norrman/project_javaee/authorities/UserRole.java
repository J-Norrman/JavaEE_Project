package com.j_norrman.project_javaee.authorities;

import java.util.Set;

import static com.j_norrman.project_javaee.authorities.UserPermission.*;

public enum UserRole {
    ADMIN(Set.of(UserPermission.READ_WEATHER, UserPermission.UPDATE_PROFILE, UserPermission.DELETE_PROFILE)),
    USER(Set.of(UserPermission.READ_WEATHER, UserPermission.UPDATE_PROFILE, UserPermission.DELETE_ACCOUNT)),
    GUEST(Set.of(UserPermission.READ_WEATHER));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
