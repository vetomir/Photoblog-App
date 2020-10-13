package pl.gregorymartin.b01.security.service.role;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.security.repository.RoleRepository;

@Service
public
class RoleModify {
    private static int PAGE_SIZE = 25;
    private RoleRepository roleRepository;

    public RoleModify(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void deleteRole(long id){
        roleRepository.deleteById(id);
    }

}
