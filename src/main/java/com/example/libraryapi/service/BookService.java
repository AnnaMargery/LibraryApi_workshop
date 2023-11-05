package com.example.libraryapi.service;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.model.BookModel;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.util.mapper.BookMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class BookService {
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private final BookRepository bookRepository;


    public List<BookDto> getBookList() {
        List<BookModel> books = bookRepository.findAll();
        List<BookDto> booksDto = new ArrayList<>();
        for (BookModel book : books) {
            BookDto bookDto = BookMapper.toBookDto(book);
            booksDto.add(bookDto);
        }
        return booksDto;
    }

    public BookDto getBookById(Long id) {
        BookModel bookModel = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return BookMapper.toBookDto(bookModel);
    }

    public BookModel addBook(BookDto bookToAdd) {
        BookModel bookModel = BookMapper.toBookModel(bookToAdd);
        return bookRepository.save(bookModel);
    }

    public BookModel updateBook(BookDto bookDto) {
        BookModel bookModel = BookMapper.toBookModel(bookDto);
        return bookRepository.save(bookModel);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    public List<BookDto> findBooksBeforeYear(Integer year) {
        List<BookModel> bookModels = bookRepository.groupBooksBeforeYear(year);
        List<BookDto> bookDtos = new ArrayList<>();
        for (BookModel bookModel : bookModels) {
            bookDtos.add(BookMapper.toBookDto(bookModel));
        }
        return bookDtos;
    }

    public List<BookDto> findByAuthorStartsWith(String letter) {
        List<BookModel> byAuthorStartsWith = bookRepository.findByAuthorStartsWith(letter);
        List<BookDto> byAuthorStartsWithDto = new ArrayList<>();
        for (BookModel bookModel : byAuthorStartsWith) {
            byAuthorStartsWithDto.add(BookMapper.toBookDto(bookModel));
        }
        return byAuthorStartsWithDto;
    }

    public List<BookDto> findByAuthor(String author) {
        List<BookModel> bookModels = bookRepository.groupBooksByAuthor(author);
        return bookModels.stream()
                .map(bookModel -> BookMapper.toBookDto(bookModel))
                .collect(Collectors.toList());
    }

    public List<BookDto> findByTitle(String title) {
        List<BookModel> bookModels = bookRepository.groupBooksByTitle(title);
        return bookModels.stream()
                .map(bookModel -> BookMapper.toBookDto(bookModel))
                .collect(Collectors.toList());
    }
}
