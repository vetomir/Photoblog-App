package pl.gregorymartin.b01.security.mapping;


import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleMapper {
    //create
    public static List<Role> mapRoleWriteModelToRoleEntity(List<RoleWriteModel> roleDaos) {
        return roleDaos.stream()
                .map(RoleMapper::mapRoleWriteModelToRoleEntity)
                .collect(Collectors.toList());
    }

    public static Role mapRoleWriteModelToRoleEntity(RoleWriteModel roleDao) {
        return new Role(roleDao.getName());
    }

    //read
    public static List<RoleReadModel> mapRoleEntityToRoleReadModel(List<Role> roleList){
        return roleList.stream()
                .map(RoleMapper::mapRoleEntityToRoleReadModel)
                .collect(Collectors.toList());
    }

    public static RoleReadModel mapRoleEntityToRoleReadModel(Role role) {
        List<String> listUserNames = new ArrayList<>();
        if(role.getUsers() != null) {
            listUserNames = role.getUsers().stream()
                    .map(User::getName)
                    .collect(Collectors.toList());
        }

        return RoleReadModel.builder()
                .id(role.getId())
                .name(role.getName())
                .users(listUserNames)
                .build();
    }

}
