package pl.gregorymartin.b01.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.core.mapping.PostMapper;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.security.mapping.RoleMapper;
import pl.gregorymartin.b01.security.mapping.UserMapper;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.RoleRepository;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public
class UserModify {
    private static int PAGE_SIZE = 25;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserModify(final UserRepository userRepository, final RoleRepository roleRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //add role
    //modify role
    //delete role
    //edit user
    //delete user

    public RoleReadModel addRole(Role role) {
        return RoleMapper.mapToRoleReadModel(roleRepository.save(role));
    }

}
