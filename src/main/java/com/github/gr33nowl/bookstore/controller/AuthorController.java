package com.github.gr33nowl.bookstore.controller;

import com.github.gr33nowl.bookstore.model.Author;
import com.github.gr33nowl.bookstore.service.AuthorService;
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
@RequestMapping("/api/authors")
@Slf4j
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Author> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable int id) {
        log.info("getById {}", id);
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("/{id}/with-books")
    public Author getWithBooks(@PathVariable int id) {
        log.info("getWithBooks {}", id);
        return service.getWithBooks(id);
    }

    @GetMapping("/by-firstname-and-lastname")
    public ResponseEntity<Author> getByFirstNameAndLastName(@RequestParam String firstName,
                                                            @RequestParam String lastName) {
        log.info("getByFirstNameAndLastName {} {}", firstName, lastName);
        return ResponseEntity.of(service.findByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping()
    public ResponseEntity<Author> create(@Valid @RequestBody Author author) {
        log.info("create {}", author);
        if(!author.isNew()) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author id is not null"); }
        Author created = service.save(author);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/authors/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable int id, @Valid @RequestBody Author author) {
        log.info("update {} with id {}", author, id);
        Optional<Author> authorToUpdate = service.getById(id);
        if(authorToUpdate.isEmpty()) {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author to update is not found");}
        service.save(author);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable int id) {
        log.info("delete {}", id);
        Optional<Author> authorToDelete = service.getById(id);
        if(authorToDelete.isEmpty()) {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author to delete is not found");}
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
