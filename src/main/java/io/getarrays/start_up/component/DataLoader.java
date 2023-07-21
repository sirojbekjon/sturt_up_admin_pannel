package io.getarrays.start_up.component;

import io.getarrays.start_up.entity.enums.Permession;
import io.getarrays.start_up.entity.Role;
import io.getarrays.start_up.entity.User;
import io.getarrays.start_up.repository.RoleRepository;
import io.getarrays.start_up.repository.UserRepository;
import io.getarrays.start_up.utils.Appconstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import static io.getarrays.start_up.entity.enums.Permession.*;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initalization-mode}")
    public String initializate;

    @Override
    public void run(String... args){
        if (initializate.equals("always")){
            Permession[] permessions = Permession.values();

            Role superAdmin = roleRepository.save(new Role(
                    Appconstants.SUPERADMIN,
                    Arrays.asList(permessions)
            ));
            Role admin = roleRepository.save(new Role(
                    Appconstants.ADMIN,
                    Arrays.asList(ADD_USER,VIEW_USER,VIEW_POST,DELETE_POST)
            ));
            Role user = roleRepository.save(new Role(
                    Appconstants.USER,
                    Arrays.asList(ADD_POST,VIEW_POST)
            ));
            userRepository.save(new User(
                    "superAdmin",
                    "superAdmin",
                    passwordEncoder.encode("superAdmin"),
                    superAdmin,
                    true
            ));
            userRepository.save(new User(
               "admin",
               "admin",
               passwordEncoder.encode("admin"),
               admin,
               true
            ));
            userRepository.save(new User(
                    "user",
                    "user",
                    passwordEncoder.encode("user"),
                    user,
                    true
            ));
        }

    }
}



