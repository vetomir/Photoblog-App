package pl.gregorymartin.b01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.core.repository.CommentRepository;
import pl.gregorymartin.b01.core.repository.PostRepository;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    @Profile("test")
    DataSource test_dataSource_forRepositories(){
        DriverManagerDataSource result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa","");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }
    @Bean
    @Primary
    @Profile("integration")
    public CommentRepository testCommentRepository() {
        return new CommentRepository() {
            private Map<Long, Comment> comments = new HashMap<>();

            @Override
            public Optional<Comment> findById(final long id) {
                return Optional.empty();
            }

            @Override
            public boolean deleteById(final long id) {
                comments.remove(id);
                return true;
            }

            @Override
            public Comment save(final Comment entity) {
                long key = comments.size() + 1;
                Field field = null;
                try {
                    field = Comment.class.getSuperclass().getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity,key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                comments.put(key, entity);
                return comments.get(key);
            }
        };
    }
    @Bean
    @Primary
    @Profile("integration")
    public PostRepository testPostRepository() {
        return new PostRepository() {
            private Map<Long, Post> posts = new HashMap<>();

            @Override
            public Optional<Post> findById(final long id) {
                return Optional.empty();
            }

            @Override
            public boolean deleteById(final long id) {
                posts.remove(id);
                return true;
            }

            @Override
            public List<Post> findAll() {
                return new ArrayList<>(posts.values());
            }

            @Override
            public List<PostInListReadModel> findAllAndMapToDto(final Pageable page) {
                List<Post> listPost = new ArrayList<>(posts.values());
                return listPost.stream()
                        .map(x -> new PostInListReadModel(
                                x.getDescription(),
                                x.getPhotoUrl(),
                                x.getCreatedOn(),
                                x.getNumberOfComments(),
                                x.getNumberOfComments(),
                                true))
                        .collect(Collectors.toList());
            }

            @Override
            public Post save(final Post entity) {
                long key = posts.size() + 1;
                Field field = null;
                try {
                    field = Comment.class.getSuperclass().getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity,key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                posts.put(key, entity);
                return posts.get(key);
            }
        };
    }
    @Bean
    @Primary
    @Profile("integration")
    public UserRepository testUserRepository() {
        return new UserRepository() {
            private Map<Long, User> users = new HashMap<>();

            @Override
            public List<User> findAll() {
                return new ArrayList<>(users.values());
            }

            @Override
            public Optional<User> findById(final long id) {
                return Optional.empty();
            }

            @Override
            public void deleteById(final long id) {
                users.remove(id);
            }

            @Override
            public User save(final User entity) {
                long key = users.size() + 1;
                Field field = null;
                try {
                    field = Comment.class.getSuperclass().getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity,key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                users.put(key, entity);
                return users.get(key);
            }

            @Override
            public List<Post> findAllPostsByUser_Name(final String name, final Pageable page) {
                return null;
            }

            @Override
            public Page<User> findAll(final Pageable pageable) {
                return null;
            }

            @Override
            public User findAllByUsername(final String username) {
                return null;
            }

            @Override
            public User findByName(final String name) {
                return null;
            }
        };
    }
}
