package com.danielduhau.ImoveisApi.service;

import com.danielduhau.ImoveisApi.model.AppUser;
import com.danielduhau.ImoveisApi.model.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String username, String roleName);
    AppUser getAppUser(String username);
    List<AppUser>getAppUsers();

}
