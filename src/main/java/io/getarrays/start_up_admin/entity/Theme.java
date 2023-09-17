package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Theme extends AbstractEntity {

    @Column(nullable = false)
    private float number;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean state;
    @Column(nullable = false)
    private boolean status2;

    @ManyToOne
    private PartOfTheme partOfTheme;
    @OneToOne
    private FileUpload fileUpload;

    public Theme(float number, String name, boolean state, PartOfTheme partOfTheme, FileUpload fileUpload) {
        this.number = number;
        this.name = name;
        this.state = state;
        this.partOfTheme = partOfTheme;
        this.fileUpload = fileUpload;
    }
}
