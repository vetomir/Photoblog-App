package pl.gregorymartin.b01.security.service.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.gregorymartin.b01.security.mapping.UserMapper;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.util.List;

@Service
public
class UserGet {
    private static int PAGE_SIZE = 25;
    private UserRepository userRepository;

    public UserGet(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserReadModel> getUsers(int page, Sort.Direction sort, String sortBy){
        return UserMapper.mapUserEntityToUserReadModel(userRepository.findAll(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ));
    }
}
