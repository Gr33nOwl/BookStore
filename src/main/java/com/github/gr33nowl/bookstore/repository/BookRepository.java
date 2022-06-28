package com.github.gr33nowl.bookstore.repository;

import com.github.gr33nowl.bookstore.model.Book;
import com.github.gr33nowl.bookstore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //https://stackoverflow.com/questions/71642208/parameter-value-gabrek-did-not-match-expected-type-java-lang-character-n-a
    Optional<List<Book>> findByTitleContainsIgnoreCase(String title);

    Optional<List<Book>> findByAuthorsId(int id);

    Optional<List<Book>> findByAuthorsLastName(String lastName);

    Optional<List<Book>> findByGenresIn(Collection<Genre> genres);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book b WHERE b.id=:id")
    void delete(@Param("id") int id);
}
