package com.example.libraryapi.repository;

import com.example.libraryapi.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {


        @Query("SELECT b FROM BookModel b WHERE b.author =:searchedAuthor")
    List<BookModel> groupBooksByAuthor(@Param("searchedAuthor") String searchedAuthor);


    @Query("SELECT b FROM BookModel b WHERE b.yearOfRelease <:year")
    List<BookModel> groupBooksBeforeYear(@Param("year") Integer year);

    @Query("SELECT b FROM BookModel b WHERE b.title =:title")
    List<BookModel> groupBooksByTitle(@Param("title") String title);

    List<BookModel> findByAuthorStartsWith(String letter);

    List<BookModel> findByYearOfReleaseBefore(Integer year);


}
