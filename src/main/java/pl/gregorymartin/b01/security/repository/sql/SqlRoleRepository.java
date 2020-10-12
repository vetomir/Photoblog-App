package pl.gregorymartin.b01.security.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.repository.RoleRepository;

import java.util.Optional;

interface SqlRoleRepository extends RoleRepository, JpaRepository<Role, Long> {
    Optional<Role> findByName(String nameOfRole);

    boolean existsByName(String roleName);
}
