package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.enums.Permession;
import io.getarrays.start_up.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "Users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    private String name;
    private String username;
    private String lastName;
    private String password;
    private String emailCode;

    @ManyToOne
    private FileUpload fileUpload;

    private String type;
    private String type2;

    @OneToMany
    private List<Subjects> subjectsList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean enabled;


    public User(String name, String username, String lastName, String password, String emailCode, Role role, boolean enabled) {
        this.name = name;
        this.username = username;
        this.lastName = lastName;
        this.password = password;
        this.emailCode = emailCode;
        this.role = role;
        this.enabled = enabled;
    }

    public User(String name, String username, String password, Role roleId, Boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = roleId;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()  {

        List<Permession> permissions = this.role.getPermission();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permession permission : permissions) {
// 1-usul   grantedAuthorities.add((GrantedAuthority) permission::name);
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;
    }
}