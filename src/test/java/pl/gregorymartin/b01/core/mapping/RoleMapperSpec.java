package pl.gregorymartin.b01.core.mapping;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.gregorymartin.b01.security.mapping.RoleMapper;
import pl.gregorymartin.b01.security.mapping.UserMapper;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.RoleRepository;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RoleMapperSpec {

    @Test
    void should_mapRoleWriteModelToRoleEntity_andReturnsGivenContent() {
        //given
        RoleWriteModel roleWriteModel = new RoleWriteModel("ROLE_USER");
        //when
        Role role = RoleMapper.mapRoleWriteModelToRoleEntity(roleWriteModel);
        //then
        Assert.assertEquals("ROLE_USER", role.getName());
    }

    @Test
    void should_mapUserEntityToUserReadModel_andReturnsGivenEntity() {
        //given
        Role role = new Role("ROLE_TEST");
        role.setId(9999L);
        //when
        RoleReadModel roleReadModel = RoleMapper.mapRoleEntityToRoleReadModel(role);
        //then
        Assert.assertEquals(role.getName(), roleReadModel.getName());
    }
}
