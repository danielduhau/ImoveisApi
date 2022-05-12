package com.danielduhau.ImoveisApi.service;

import com.danielduhau.ImoveisApi.model.AppUser;
import com.danielduhau.ImoveisApi.model.Role;
import com.danielduhau.ImoveisApi.repository.AppUserRepository;
import com.danielduhau.ImoveisApi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    //Faz a autenticação de usuário carregando pelo banco de dados.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null){
            log.error("appUser not found in the database.");
            throw new UsernameNotFoundException("appUser not found in the database.");
        } else {
            log.info("appUser found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new user {} to the database", appUser.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
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
