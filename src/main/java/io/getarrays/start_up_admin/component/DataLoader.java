package io.getarrays.start_up_admin.component;

import io.getarrays.start_up_admin.entity.Role;
import io.getarrays.start_up_admin.entity.Teacher;
import io.getarrays.start_up_admin.entity.enums.Permession;
import io.getarrays.start_up_admin.repository.RoleRepository;
import io.getarrays.start_up_admin.repository.TeachersRepository;
import io.getarrays.start_up_admin.utils.Appconstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TeachersRepository teachersRepository;



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
                    Arrays.asList(Permession.ADD_POST, Permession.VIEW_POST, Permession.EDIT_POST)
            ));

            teachersRepository.save(new Teacher(
                    "superAdmin",
                    "superAdmin",
                    passwordEncoder.encode("superAdmin"),
                    superAdmin,
                    true
            ));

            teachersRepository.save(new Teacher(
               "admin",
               "admin",
               passwordEncoder.encode("admin"),
               admin,
               true
            ));

        }

    }
}




