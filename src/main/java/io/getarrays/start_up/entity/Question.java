package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question extends AbstractEntity {

    private String text;
    private String keyA;
    private String keyB;
    private String keyC;
    private String keyD;
    private String ansverKey;

    @ManyToOne
    private Subjects subject;

    @ManyToOne
    private ThemeSubject themeSubject;
}
