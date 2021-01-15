package io.naimi.authservice.Repositories;

import io.naimi.authservice.Entities.AppRole;
import io.naimi.authservice.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
