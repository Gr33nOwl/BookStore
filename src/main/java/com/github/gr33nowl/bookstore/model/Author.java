package com.github.gr33nowl.bookstore.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
