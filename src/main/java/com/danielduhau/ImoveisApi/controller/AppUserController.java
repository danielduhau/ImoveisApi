package com.danielduhau.ImoveisApi.controller;

import com.danielduhau.ImoveisApi.model.AppUser;
import com.danielduhau.ImoveisApi.model.Role;
import com.danielduhau.ImoveisApi.service.AppUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getUsers(){
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser>saveUser(@RequestBody AppUser appUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveAppUser(appUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/user/addrole")
    public ResponseEntity<?>addRoleToAppUser(@RequestBody RoleToUserForm form){
        appUserService.addRoleToAppUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}