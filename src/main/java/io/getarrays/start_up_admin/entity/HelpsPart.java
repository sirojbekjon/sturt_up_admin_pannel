package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HelpsPart extends AbstractEntity {
    @Column(columnDefinition = "Text")
    private String text;

    @ManyToOne
    private HelpsType helpsType;

    @ManyToOne
    private FileUpload fileUpload;
}
