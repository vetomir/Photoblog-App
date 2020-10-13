package pl.gregorymartin.b01.security.service.user;

import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.security.mapping.UserMapper;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public
class UserModify {
    private UserRepository userRepository;

    public UserModify(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserReadModel changeUserEmail(UserWriteModel userWriteModel){
        Optional<User> user = userRepository.findById(userWriteModel.getId());
        user.get().setEmail(userWriteModel.getEmail());
        return UserMapper.mapUserEntityToUserReadModel(userRepository.save(user.get()));

    }
    @Transactional
    public boolean changeUserPassword(UserWriteModel userWriteModel){
        Optional<User> user = userRepository.findById(userWriteModel.getId());
        if(userWriteModel.getPassword().equals(userWriteModel.getPassword2())){
            user.get().setPassword(userWriteModel.getPassword());
            userRepository.save(user.get());
            return true;
        }
        else throw new IllegalArgumentException("Passwords are not the same");
    }
    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
