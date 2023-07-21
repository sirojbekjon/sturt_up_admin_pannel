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

@Table(name = "teachers")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends AbstractEntity implements UserDetails {

    private String name;
    private String sureName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;

    @Column(columnDefinition = "TEXT")
    private String about;

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
    private boolean enabled=true;

    public Teacher(String name, String username, String password, Role role, boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }


    public Teacher(String name, String sureName, String lastName, String phoneNumber, String username, String password, String about, FileUpload fileUpload, List<Subjects> subjectsList, Role role) {
        this.name = name;
        this.sureName = sureName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.about = about;
        this.fileUpload = fileUpload;
        this.subjectsList = subjectsList;
        this.role = role;
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




