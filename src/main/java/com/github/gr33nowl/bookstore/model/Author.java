package com.github.gr33nowl.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author extends BaseEntity{

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    private String lastName;

    @JsonIgnoreProperties("authors")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author(Integer id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
