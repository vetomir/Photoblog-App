package pl.gregorymartin.b01.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.gregorymartin.b01.core.model.Audit;
import pl.gregorymartin.b01.core.model.Comment;
import pl.gregorymartin.b01.core.model.Post;
import org.springframework.security.core.userdetails.UserDetails;
import pl.gregorymartin.b01.core.model.Rate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends Audit implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    @Email
    private String email;
    private String avatar;
    private boolean isEnabled;

    @OneToMany//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private List<Post> posts;

    @OneToMany//(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private List<Comment> comments;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Rate rate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    Set<Role> roles;

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        setDefaultPhoto();
        roles = new HashSet<>();
        this.isEnabled = true;
    }

    private void setDefaultPhoto(){
        this.avatar = "https://p1.hiclipart.com/preview/571/108/16/person-silhouette-shadow-man-shadow-person-human-portrait-face-png-clipart.jpg";
    }
    public int getNumberOfLikes(){
        return getRate().getPosts().size();
    }
    public int getNumberOfComments(){
        return getComments().size();
    }
    public int getNumberOfPosts(){
        return getPosts().size();
    }

    public void newRole(Role newRole){
        this.roles.add(newRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        roles.forEach(x -> authorities.add(new SimpleGrantedAuthority("ROLE_" + x.getName().toUpperCase())));
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void toggleEnable() {
        isEnabled = !isEnabled;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void toUpdate(User toUpdate){
        this.name = toUpdate.name;
        this.username = toUpdate.username;
        this.email = toUpdate.email;
    }
}
