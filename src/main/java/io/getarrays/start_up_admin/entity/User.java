package io.getarrays.start_up_admin.entity;


import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "Users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity{

    private String name;
    private String username;
    private String lastName;
    private String email;
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

}