package io.getarrays.start_up.entity;

import io.getarrays.start_up.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.awt.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class News extends AbstractEntity {

       private String name;

       private TextArea text;

       @OneToMany
       private List<FileUpload> fileUploadList;
}
