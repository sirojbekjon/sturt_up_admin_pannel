package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.enums.Permession;
import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permession> permission;

}
