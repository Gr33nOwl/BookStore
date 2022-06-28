package com.github.gr33nowl.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(max = 256)
    private String title;

    @JsonIgnoreProperties("books")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "genre")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Genre> genres = new HashSet<>();

    @Column(name = "publication_date", nullable = false, updatable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @Column(name = "price", nullable = false)
    @NotNull
    @DecimalMin("0.1")
    @DecimalMax("500.0")
    private double price;

    @Column(name = "amount", nullable = false)
    @NotNull
    @Min(0)
    private int amount;


    public Book(Integer id, String title, Set<Author> authors, Set<Genre> genres, LocalDate publicationDate, double price, int amount) {
        super(id);
        this.title = title;
        this.authors = authors;
        setGenres(genres);
        this.publicationDate = publicationDate;
        this.price = price;
        this.amount = amount;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = CollectionUtils.isEmpty(genres) ? EnumSet.noneOf(Genre.class) : EnumSet.copyOf(genres);
    }
}

