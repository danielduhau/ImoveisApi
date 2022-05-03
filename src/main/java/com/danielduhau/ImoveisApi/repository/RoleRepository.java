package com.danielduhau.ImoveisApi.repository;

import com.danielduhau.ImoveisApi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
