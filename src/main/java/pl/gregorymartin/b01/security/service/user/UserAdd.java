package pl.gregorymartin.b01.security.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
    private static String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserAdd(final UserRepository userRepository, final RoleRepository roleRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserReadModel registerUser(UserWriteModel userWriteModel){
        Optional<User> byUsername = Optional.ofNullable(userRepository.findAllByUsername(userWriteModel.getEmail()));
        Optional<User> byName = userRepository.findByName(userWriteModel.getName());
        if(byUsername.isPresent() || byName.isPresent()){
            throw new IllegalArgumentException("User with these username (email) or name is already exists");
        }
        else{
            User result = UserMapper.mapUserWriteModelToUserEntity(userWriteModel);
            Optional<Role> roleByName = roleRepository.findByName(DEFAULT_ROLE);
            result.newRole(roleByName.get());
            result.setPassword(passwordEncoder.encode(userWriteModel.getPassword()));

            return UserMapper.mapUserEntityToUserReadModel(userRepository.save(result));
        }
    }

}
