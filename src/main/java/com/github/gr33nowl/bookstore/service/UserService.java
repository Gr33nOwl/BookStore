package com.github.gr33nowl.bookstore.service;

import com.github.gr33nowl.bookstore.model.User;
import com.github.gr33nowl.bookstore.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "email"));
    }

    public Optional<User> getById(int id) {
        return repository.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

}
