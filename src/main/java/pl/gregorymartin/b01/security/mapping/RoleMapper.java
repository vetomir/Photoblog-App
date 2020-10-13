package pl.gregorymartin.b01.security.mapping;


import lombok.RequiredArgsConstructor;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleMapper {

    //read
    public static List<RoleReadModel> mapRoleEntitiesToRoleReadModels(List<Role> roleList){
        return roleList.stream()
                .map(RoleMapper::mapRoleEntitiesToRoleReadModels)
                .collect(Collectors.toList());
    }
    public static RoleReadModel mapRoleEntitiesToRoleReadModels(Role role) {
        return RoleReadModel.builder()
                .id(role.getId())
                .name(role.getName())
                .users(role.getUsers().stream().map(User::getName).collect(Collectors.toList()))
                .build();
    }

    //save
    public static List<Role> mapRoleWriteModelsToRoleEntities(List<RoleWriteModel> roleDaos) {
        return roleDaos.stream()
                .map(RoleMapper::mapRoleWriteModelToRoleEntity)
                .collect(Collectors.toList());
    }
    public static Role mapRoleWriteModelToRoleEntity(RoleWriteModel roleDao) {
        return new Role(roleDao.getName());
    }
}
