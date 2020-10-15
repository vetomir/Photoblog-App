package pl.gregorymartin.b01.security.mapping;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper {
    //create
    public static List<User> mapUserWriteModelToUserEntity(List<UserWriteModel> userDaos) {
        return userDaos.stream()
                .map(UserMapper::mapUserWriteModelToUserEntity)
                .collect(Collectors.toList());
    }

    public static User mapUserWriteModelToUserEntity(UserWriteModel userDao) {
        if(userDao.getPassword2().equals(userDao.getPassword())){
            User user = new User();
            user.setUsername(userDao.getEmail());
            user.setPassword(user.getPassword());

            return user;
        }
        else throw new IllegalArgumentException("Passwords, ale not the same!");

    }
    //read

    public static List<UserReadModel> mapUserEntityToUserReadModel(Page<User> userList){
        return userList.stream()
                .map(UserMapper::mapUserEntityToUserReadModel)
                .collect(Collectors.toList());
    }
    public static UserReadModel mapUserEntityToUserReadModel(User user) {

        return UserReadModel.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getUsername())
                .avatar(user.getAvatar())
                .createdOn(user.formatCreatedOn())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
    }
}
