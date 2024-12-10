package com.yael.springcloud.msv.users.services;

import java.util.List;
import java.util.Optional;

import com.yael.springcloud.msv.users.entities.User;




public interface IUserService {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findUserByUsername(String username);
    List<User> findAll();
    void deleteUser( Long id );

}
