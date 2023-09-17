package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartOfTheme extends AbstractEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float number;

    @ManyToOne
    private ThemeSubject themeSubject;
}
