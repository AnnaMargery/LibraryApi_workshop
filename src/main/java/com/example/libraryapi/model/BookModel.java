package com.example.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Author")
    private String author;
    @Column(name = "Year_of_release")
    private Integer yearOfRelease;
}
