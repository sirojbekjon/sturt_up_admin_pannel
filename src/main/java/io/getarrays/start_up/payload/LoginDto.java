package io.getarrays.start_up.payload;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Data
public class LoginDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
