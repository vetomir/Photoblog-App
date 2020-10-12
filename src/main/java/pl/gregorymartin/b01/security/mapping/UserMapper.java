package pl.gregorymartin.b01.security.mapping;


import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.core.mapping.CommentMapper;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMapper {


    //create
    public static List<User> mapDaosToEntity(List<UserWriteModel> userDaos) {
        return userDaos.stream()
                .map(user -> mapDaoToEntity(user))
                .collect(Collectors.toList());
    }

    public static User mapDaoToEntity(UserWriteModel userDao) {
        if(userDao.getPassword2().equals(userDao.getPassword())){
            User user = new User();
            user.setUsername(userDao.getEmail());
            user.setRoles(Collections.singleton(new Role("USER_ROLE")));

            return user;
        }
        else throw new IllegalArgumentException("Passwords, ale not the same!");

    }
    //read

    public static List<UserReadModel> mapToUserReadModels(List<User> userList){
        return userList.stream()
                .map(UserMapper::mapToUserReadModel)
                .collect(Collectors.toList());
    }
    public static UserReadModel mapToUserReadModel(User user) {

        return UserReadModel.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatar())
                .roles(user.getRoles().stream().map(x -> x.getName()).collect(Collectors.toList()))
                .email(user.getEmail())
                .build();
    }
}
