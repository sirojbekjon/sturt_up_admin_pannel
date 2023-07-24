package io.getarrays.start_up_admin.repository;

import io.getarrays.start_up_admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
