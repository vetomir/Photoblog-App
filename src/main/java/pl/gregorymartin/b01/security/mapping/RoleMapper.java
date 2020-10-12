package pl.gregorymartin.b01.security.mapping;


import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleMapper {

    //read
    public static List<RoleReadModel> mapToRoleReadModels(List<Role> roleList){
        return roleList.stream()
                .map(RoleMapper::mapToRoleReadModel)
                .collect(Collectors.toList());
    }
    public static RoleReadModel mapToRoleReadModel(Role role) {

        return RoleReadModel.builder()
                .id(role.getId())
                .name(role.getName())
                .users(role.getUsers().stream().map(User::getName).collect(Collectors.toList()))
                .build();
    }
}
