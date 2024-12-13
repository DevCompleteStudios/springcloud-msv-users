package com.yael.springcloud.msv.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springcloud.msv.users.entities.Role;
import com.yael.springcloud.msv.users.entities.User;
import com.yael.springcloud.msv.users.repositories.RoleRepository;
import com.yael.springcloud.msv.users.repositories.UserRepository;


@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    @Transactional
    public User save(User user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");

        if( user.getIsAdmin() != null && user.getIsAdmin().equals(true) ){
            roleRepository.findByName("ROLE_ADMIN")
                .ifPresent( roles::add );
        }

        roleOptional.ifPresent( roles::add );
        user.setRoles(roles);
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly=true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional()
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
