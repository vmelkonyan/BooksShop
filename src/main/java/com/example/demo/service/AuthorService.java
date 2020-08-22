package com.example.demo.service;

import com.example.demo.domian.Author;
import com.example.demo.domian.Book;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.repo.AuthorRepo;
import com.example.demo.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    public void save(Author author) {
        authorRepo.save(author);
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    public Page<Author> findAll(Pageable pageable) {
        return authorRepo.findAll(pageable);
    }

    public Author findAllByFirstName(String firstName) {
        return authorRepo.findAllByFirstName(firstName);
    }

    public Set<Author> findByIdIn(List<Long> ids) {
        return authorRepo.findByIdIn(ids);
    }

    public void saveAll(List<Author> authors){
        authorRepo.saveAll(authors);
    }
}
