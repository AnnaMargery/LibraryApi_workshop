package com.example.libraryapi.util.mapper;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.model.BookModel;

public class BookMapper {


    public static BookDto toBookDto (BookModel bookModel){
        BookDto bookDto = new BookDto();
        bookDto.setAuthor(bookModel.getAuthor());
        bookDto.setTitle(bookModel.getTitle());
        bookDto.setYearOfRelease(bookModel.getYearOfRelease());
        return bookDto;
    }

    public static BookModel toBookModel (BookDto bookDto){
        BookModel bookModel = new BookModel();
        bookModel.setAuthor(bookDto.getAuthor());
        bookModel.setTitle(bookDto.getTitle());
        bookModel.setYearOfRelease(bookDto.getYearOfRelease());
        return bookModel;
    }

}
