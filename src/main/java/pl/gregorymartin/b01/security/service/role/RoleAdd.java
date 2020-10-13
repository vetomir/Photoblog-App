package pl.gregorymartin.b01.security.service.role;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.security.mapping.RoleMapper;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.repository.RoleRepository;

@Service
public
class RoleAdd {
    private RoleRepository roleRepository;

    public RoleAdd(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleReadModel addRole(RoleWriteModel role) {
        return RoleMapper.mapRoleEntityToRoleReadModel(roleRepository.save(RoleMapper.mapRoleWriteModelToRoleEntity(role)));
    }
}
