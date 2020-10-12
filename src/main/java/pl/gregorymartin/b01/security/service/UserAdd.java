package pl.gregorymartin.b01.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gregorymartin.b01.security.mapping.UserMapper;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.RoleRepository;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.util.Optional;

@Service
public
class UserAdd {
    private static String DEFAULT_ROLE = "USER_ROLE";
    private static int PAGE_SIZE = 25;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserAdd(final UserRepository userRepository, final RoleRepository roleRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserReadModel registerUser(UserWriteModel userWriteModel){
        User result = UserMapper.mapDaoToEntity(userWriteModel);
        Optional<Role> roleByName = roleRepository.findByName(DEFAULT_ROLE);
        result.newRole(roleByName.get());
        result.setPassword(passwordEncoder.encode(result.getPassword()));
        UserReadModel userReadModel = UserMapper.mapToUserReadModel(userRepository.save(result));

        return userReadModel;
    }

}
