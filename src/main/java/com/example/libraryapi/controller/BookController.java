package com.example.libraryapi.controller;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.model.BookModel;
import com.example.libraryapi.service.BookService;
import com.example.libraryapi.util.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping()
    public ResponseEntity<List<BookDto>> getBooksList() {
        try {
            List<BookDto> books = bookService.getBookList();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        try {
            BookDto book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookModel> addBook(@RequestBody BookDto bookToAdd) {
        try {
            BookModel bookModel = bookService.addBook(bookToAdd);
            return ResponseEntity.ok(bookModel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public void updateBook(@RequestBody BookDto bookDto) {
        try {
            BookModel updateBook = bookService.updateBook(bookDto);
            ResponseEntity.ok(updateBook);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //todo zmienic na dto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //todo zmienic na dto
    @GetMapping("/before/{year}")
    public ResponseEntity<List<BookModel>> findBooksBeforeYear(@PathVariable Integer year) {
        try {
            List<BookModel> booksBeforeYear = bookService.findBooksBeforeYear(year);
            return ResponseEntity.ok(booksBeforeYear);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //todo zmienic na dto
    @GetMapping("/author/{letter}")
    public ResponseEntity<List<BookModel>> findBooksByAuthorStartsWith(@PathVariable String letter) {
        try {
            List<BookModel> byAuthorStartsWith = bookService.findByAuthorStartsWith(letter);
            return ResponseEntity.ok(byAuthorStartsWith);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //todo zmienic na dto
    @GetMapping("/author")
    public ResponseEntity<List<BookModel>> findBooksByAuthor(@Param("author") String author) {
        try {
            List<BookModel> booksByAuthor = bookService.findByAuthor(author);
            return ResponseEntity.ok(booksByAuthor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //todo zmienic na dto
    @GetMapping("/title")
    public ResponseEntity<List<BookModel>> findBooksByTitle(@Param("title") String title) {
        try {
            List<BookModel> booksByTitle = bookService.findByTitle(title);
            return ResponseEntity.ok(booksByTitle);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
