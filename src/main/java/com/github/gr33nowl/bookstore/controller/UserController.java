package com.github.gr33nowl.bookstore.controller;

import com.github.gr33nowl.bookstore.model.User;
import com.github.gr33nowl.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        log.info("getById {}", id);
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        log.info("getByEmail {}", email);
        return ResponseEntity.of(service.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        log.info("create {}", user);
        if (!user.isNew()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User id is not null");
        }
        User created = service.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @Valid @RequestBody User user) {
        log.info("update {} with id {}", user, id);
        Optional<User> userToUpdate = service.getById(id);
        if (userToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User to update is not found");
        }
        service.save(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable int id) {
        log.info("delete {}", id);
        Optional<User> userToDelete = service.getById(id);
        if (userToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User to delete is not found");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
