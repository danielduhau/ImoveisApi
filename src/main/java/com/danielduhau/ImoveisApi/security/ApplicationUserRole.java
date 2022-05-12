package com.danielduhau.ImoveisApi.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.danielduhau.ImoveisApi.security.ApplicationUserPermission.*;
import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    ESTUDANTE(Sets.newHashSet()),
    CORRETOR(Sets.newHashSet(IMOVEL_READ)),
    ADMIN(Sets.newHashSet(IMOVEL_READ, IMOVEL_WRITE, APPUSER_READ, APPUSER_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
