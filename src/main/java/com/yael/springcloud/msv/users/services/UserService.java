package com.yael.springcloud.msv.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springcloud.msv.users.entities.User;
import com.yael.springcloud.msv.users.repositories.UserRepository;



public class UserService implements IUserService {

    @Autowired
    UserRepository repository;


    @Override
    @Transactional
    public User save(User user) {
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
