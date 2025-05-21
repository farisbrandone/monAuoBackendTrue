package com.example.monauto;

import com.example.monauto.DTO.ImageClass;
import com.example.monauto.entity.Auto;
import com.example.monauto.entity.Role;
import com.example.monauto.entity.Seller;
import com.example.monauto.service.IAutoService;

import com.example.monauto.service.IRoleService;
import com.example.monauto.service.RoleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MonAutoApplication implements CommandLineRunner {

    private  IAutoService autoService;
    private IRoleService roleService;
    private  RepositoryRestConfiguration restConfiguration;

    public MonAutoApplication(IAutoService autoService, RepositoryRestConfiguration restConfiguration, IRoleService roleService) {
        this.autoService = autoService;
        this.restConfiguration = restConfiguration;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MonAutoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Auto.class, Seller.class, ImageClass.class, Role.class);
        autoService.initSeller();
        autoService.initAuto();
       /* Collection<String> my= Arrays.asList("ADMIN","USER","PARTICULIER", "ENTREPRISE", "STUDYACTIVATION", "DESACTIVATE" );
        Collection<Role> myRole= roleService.addRole(my);*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
