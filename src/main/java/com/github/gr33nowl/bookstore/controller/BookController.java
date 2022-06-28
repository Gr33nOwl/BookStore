package com.github.gr33nowl.bookstore.controller;

import com.github.gr33nowl.bookstore.model.Author;
import com.github.gr33nowl.bookstore.model.Book;
import com.github.gr33nowl.bookstore.model.Genre;
import com.github.gr33nowl.bookstore.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        log.info("getAll");
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable int id) {
        log.info("getById {}", id);
        return ResponseEntity.of(service.getById(id));
    }

    @GetMapping("/by-title")
    public ResponseEntity<List<Book>> getByTitle(@RequestParam String title) {
        log.info("getByTitle {}", title);
        return ResponseEntity.of(service.getByTitle(title));
    }

    @GetMapping("/by-author-id/{id}")
    public ResponseEntity<List<Book>> getByAuthorId(@PathVariable int id) {
        log.info("getBuAuthorId {}", id);
        return ResponseEntity.of(service.getByAuthorId(id));
    }

    @GetMapping("by-author-lastname")
    public ResponseEntity<List<Book>> getByAuthorLastName(@RequestParam String lastName) {
        log.info("getByAuthorLastName {}", lastName);
        return ResponseEntity.of(service.getByAuthorLastName(lastName));
    }

    @GetMapping("by-genre")
    public ResponseEntity<List<Book>> getByGenres(@RequestParam Collection<Genre> genres) {
        log.info("getByGenres {}", genres);
        return ResponseEntity.of(service.getByGenres(genres));
    }

    @PostMapping()
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        log.info("create {}", book);
        if (!book.isNew()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book id is not null");
        }
        Book created = service.save(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/books/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable int id, @Valid @RequestBody Book book) {
        log.info("update {} with id {}", book, id);
        Optional<Book> bookToUpdate = service.getById(id);
        if (bookToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book to update is not found");
        }
        service.save(book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> delete(@PathVariable int id) {
        log.info("delete {}", id);
        Optional<Book> bookToDelete = service.getById(id);
        if (bookToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book to delete is not found");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
