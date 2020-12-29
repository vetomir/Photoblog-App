package pl.gregorymartin.b01.core.mapping;

import org.junit.jupiter.api.Test;
import pl.gregorymartin.b01.security.mapping.RoleMapper;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.model.Role;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleMapperSpec {

    @Test
    void should_mapRoleWriteModelToRoleEntity_andReturnsGivenContent() {
        //given
        RoleWriteModel roleWriteModel = new RoleWriteModel("ROLE_USER");
        //when
        Role role = RoleMapper.mapRoleWriteModelToRoleEntity(roleWriteModel);
        //then
        assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void should_mapUserEntityToUserReadModel_andReturnsGivenEntity() {
        //given
        Role role = new Role("ROLE_TEST");
        role.setId(9999L);
        //when
        RoleReadModel roleReadModel = RoleMapper.mapRoleEntityToRoleReadModel(role);
        //then
        assertEquals(role.getName(), roleReadModel.getName());
    }
}
