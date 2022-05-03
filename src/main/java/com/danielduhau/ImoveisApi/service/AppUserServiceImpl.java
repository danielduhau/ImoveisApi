package com.danielduhau.ImoveisApi.service;

import com.danielduhau.ImoveisApi.model.AppUser;
import com.danielduhau.ImoveisApi.model.Role;
import com.danielduhau.ImoveisApi.repository.AppUserRepository;
import com.danielduhau.ImoveisApi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getUsername());
        return appUserRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        AppUser appUser = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);

    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Fetching user {}", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Fetching all users");
        return appUserRepository.findAll();
    }
}
