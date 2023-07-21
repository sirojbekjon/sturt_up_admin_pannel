package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;
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
