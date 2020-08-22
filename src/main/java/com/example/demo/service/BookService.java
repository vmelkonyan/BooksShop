package com.example.demo.service;

import com.example.demo.domian.Author;
import com.example.demo.domian.Book;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepo bookRepo;


    public void save(Book book) {
        bookRepo.save(book);
    }

    public Iterable<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    public Page<Book> findAllByName(String name, Pageable pageable) {
        return bookRepo.findAllByBookName(name, pageable);
    }

    public Page<Book> findByAuthorName(String name, Pageable pageable) {
        return bookRepo.findByAuthorName(name, pageable);
    }


    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Transactional
    public Book buyBookById(Long id) {

        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        if (book.getAvailableBook() > 0) {
            book.setAvailableBook(book.getAvailableBook() - 1);
            return bookRepo.save(book);
        } else
            throw new RuntimeException("");
    }



    public Page<Book> find(String search, String searchBy, Pageable pageable){

        if (StringUtils.isEmpty(search)) {
            Page<Book> bookList = bookRepo.findAll(pageable);
            return bookList;
        } else {
            Page<Book> bookList;
            if ("book".equals(searchBy)) {
                bookList = findAllByName(search, pageable);
            } else {
                bookList = findByAuthorName(search, pageable);
            }
            return bookList;
        }
    }

    public void saveAll(List<Book> books){
        bookRepo.saveAll(books);
    }

}
