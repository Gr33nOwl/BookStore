package com.github.gr33nowl.bookstore.repository;

import com.github.gr33nowl.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id=?1")
    Optional<Author> getWithBooks(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Author a WHERE a.id=:id")
    void delete(@Param("id") int id);
}
