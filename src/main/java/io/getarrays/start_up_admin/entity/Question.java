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
public class Question extends AbstractEntity {
    @Column(columnDefinition = "Text")
    private String text;
    private String keyA;
    private String keyB;
    private String keyC;
    private String keyD;
    private String ansverKey;
    @ManyToOne
    private Subjects subject;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private PartOfTheme partOfTheme;
}
