package com.example.monauto.service;

import com.example.monauto.dao.AutoRepository;
import com.example.monauto.dao.RoleRepository;
import com.example.monauto.dao.SellerRepository;
import com.example.monauto.entity.Auto;
import com.example.monauto.entity.Role;
import com.example.monauto.enumFile.RoleUser;
import com.example.monauto.randomGenerateValue.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private final AutoRepository autoRepository;
    private RoleRepository roleRepository;
    private SellerRepository sellerRepository;
    public RoleServiceImpl(RoleRepository roleRepository, SellerRepository sellerRepository, AutoRepository autoRepository) {
        this.roleRepository = roleRepository;
        this.sellerRepository = sellerRepository;
        this.autoRepository = autoRepository;
    }

    @Override
    public  Collection<Role>  addRole(Collection<String> roles) {
        Collection<Role> roleList = new ArrayList<>();
        roles.forEach(roleName -> {
            Role role = new Role();
            RoleUser roleUser= RoleUser.valueOf(roleName);
            role.setRoleName(roleUser);
       Role  myrole= roleRepository.save(role);
       roleList.add(myrole);

        });
        return roleList;
    }

    @Override
    public void initAuto() {

       /* sellerRepository.findAll().forEach(seller -> {
            Stream.of("Audi", "Toyota", "Mercedes", "BMW", "Honda", "Ford", "Renaud", "Citroen", "Nissan").forEach(auto -> {
                Auto autoEntity = new Auto();
                RandomString randomString = new RandomString();
                autoEntity.setMarques(auto);
                autoEntity.setTypeCarburant(randomString.RandomStringMethod());
                autoEntity.setSeller(seller);
                autoRepository.save(autoEntity);
            });
        });*/
    }



}
