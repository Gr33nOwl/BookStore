package com.github.gr33nowl.bookstore.repository;

import com.github.gr33nowl.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByFirstNameAndLastName (String firstName, String lastName);

    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id=?1")
    Author getWithBooks(int id);
    }
