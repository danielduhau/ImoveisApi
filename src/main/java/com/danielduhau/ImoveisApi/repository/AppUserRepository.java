package com.danielduhau.ImoveisApi.repository;

import com.danielduhau.ImoveisApi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
