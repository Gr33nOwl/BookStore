package com.github.gr33nowl.bookstore.service;

import com.github.gr33nowl.bookstore.model.Author;
import com.github.gr33nowl.bookstore.repository.AuthorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
    }

    public Optional<Author> getById(Integer id) {
        return repository.findById(id);
    }

    public Optional<Author> findByFirstNameAndLastName (String firstName, String lastName) {
        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Author getWithBooks (Integer id) {
        return repository.getWithBooks(id);
    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }


}
