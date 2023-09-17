package io.getarrays.start_up_admin.entity;

import io.getarrays.start_up_admin.entity.template.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Footer extends AbstractEntity {
    @Column(columnDefinition = "Text")
    private String text;
    private String telegram;
    private String instagram;
    private String youtube;
    private String phone;
    private boolean status;

}
