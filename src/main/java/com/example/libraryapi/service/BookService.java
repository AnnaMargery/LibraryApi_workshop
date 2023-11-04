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

@Service
@RequiredArgsConstructor
public class BookService {

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

    //todo zmienic na dto
    public List<BookModel> findBooksBeforeYear(Integer year) {
        return bookRepository.groupBooksBeforeYear(year);
    }
    //todo zmienic na dto
    public List<BookModel> findByAuthorStartsWith(String letter) {
        return bookRepository.findByAuthorStartsWith(letter);
    }
    //todo zmienic na dto
    public List<BookModel> findByAuthor(String author) {
        return bookRepository.groupBooksByAuthor(author);
    }
    //todo zmienic na dto
    public List<BookModel> findByTitle(String title) {
        return bookRepository.groupBooksByTitle(title);
    }
}
