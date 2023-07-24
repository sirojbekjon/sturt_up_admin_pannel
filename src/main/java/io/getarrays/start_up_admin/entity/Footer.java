package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Footer extends AbstractEntity {
    private TextArea text;
    private String telegram;
    private String instagram;
    private String youtube;
    private String phone;
    private boolean status;

}
