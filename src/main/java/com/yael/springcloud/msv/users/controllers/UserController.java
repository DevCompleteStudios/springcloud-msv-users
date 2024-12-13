package com.yael.springcloud.msv.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springcloud.msv.users.entities.User;
import com.yael.springcloud.msv.users.services.IUserService;



@RestController
@RequestMapping
public class UserController {

    @Autowired
    IUserService service;
    @Autowired
    PasswordEncoder encoder;


    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user ){
        user.setPassword( encoder.encode(user.getPassword()) );
        return ResponseEntity.ok(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update( @PathVariable Long id, @RequestBody User user ){
        user.setId(id);
        return ResponseEntity.ok(service.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById( @PathVariable Long id ){
        return ResponseEntity.ok(
            service.findById(id)
                .orElseThrow()
        );
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUsername( @PathVariable String username ){
        return ResponseEntity.ok(
            service.findUserByUsername(username)
                .orElseThrow()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id ){
        service.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok( service.findAll() );
    }

}
