package io.getarrays.start_up.payload;

import jdk.javadoc.internal.doclets.formats.html.markup.Text;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class Userdto {
    private String lastName;
    private String name;
    private String username;
    private String password;
    private Text about;
    private Long fileId;


}
