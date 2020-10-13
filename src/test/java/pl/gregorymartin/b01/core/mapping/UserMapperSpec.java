package pl.gregorymartin.b01.core.mapping;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.model.Tag;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.security.mapping.UserMapper;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.Role;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserMapperSpec {

    @Test
    void should_mapUserWriteModelToUserEntity_andReturnsGivenContent() {
        //given
        UserWriteModel userWriteModel = new UserWriteModel("abc@test.com", "UgaBuga123","UgaBuga123");
        //when
        User user = UserMapper.mapUserWriteModelToUserEntity(userWriteModel);
        //then
        Assert.assertEquals("abc@test.com", user.getUsername());
    }

    @Test
    void mapUserWriteModelToUserEntity_andThrowIllegalArgumentException() {
        //given
        UserWriteModel userWriteModel = new UserWriteModel("abc@test.com", "Buga123Uga","Ugabuga123");
        //when
        Throwable exception = catchThrowable(() -> UserMapper.mapUserWriteModelToUserEntity(userWriteModel));
        //then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Passwords, ale not the same!");
    }

    @Test
    void should_mapPostWriteModelsToEntity_forList_andReturnsGivenContents() {
        //given
        List<UserWriteModel> list = new ArrayList<>();
        list.add(new UserWriteModel("abc@test.com", "UgaBuga123","UgaBuga123"));
        list.add(new UserWriteModel("abc1@test.com", "UgaBuga","UgaBuga"));
        //when
        List<User> userList = UserMapper.mapUserWriteModelToUserEntity(list);
        //then
        Assert.assertEquals("abc@test.com", userList.get(0).getUsername());
        Assert.assertEquals("abc1@test.com", userList.get(1).getUsername());
        Assert.assertEquals(2, userList.size());
    }

    @Test
    void should_mapUserEntityToUserReadModel_andReturnsGivenEntity() {
        //given
        User user = new User("TEST_NAME", "abc@test.com","UgaBuga123");
        user.setCreatedOn(LocalDateTime.now());
        user.setRoles(Set.of(new Role("ROLE_TEST"),new Role("ROLE_TEST2")));
        //and
        //when
        UserReadModel userReadModel = UserMapper.mapUserEntityToUserReadModel(user);
        //then
        Assert.assertEquals(user.getName(), userReadModel.getName());
        Assert.assertEquals(user.getUsername(), userReadModel.getEmail());
        Assert.assertEquals(2, userReadModel.getRoles().size());

    }
}
