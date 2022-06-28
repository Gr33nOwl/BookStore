package com.github.gr33nowl.bookstore.service;

import com.github.gr33nowl.bookstore.model.Book;
import com.github.gr33nowl.bookstore.model.Genre;
import com.github.gr33nowl.bookstore.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public Optional<Book> getById(int id) {
        return repository.findById(id);
    }

    public Optional<List<Book>> getByTitle(String title) {
        return repository.findByTitleContainsIgnoreCase(title);
    }

    public Optional<List<Book>> getByAuthorId(int id) {
        return repository.findByAuthorsId(id);
    }

    public Optional<List<Book>> getByAuthorLastName(String lastName) {
        return repository.findByAuthorsLastName(lastName);
    }

    public Optional<List<Book>> getByGenres(Collection<Genre> genres) {
        return repository.findByGenresIn(genres);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
