package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TypeSubject extends AbstractEntity {
    private String name;
}
