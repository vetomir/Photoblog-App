package pl.gregorymartin.b01.security.repository;

import pl.gregorymartin.b01.security.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String nameOfRole);
    boolean existsByName(String roleName);
}
