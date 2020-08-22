package com.example.demo.repo;

import com.example.demo.domian.Author;
import com.example.demo.domian.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface AuthorRepo extends CrudRepository<Author, Long> {

    Page<Author> findAll(Pageable pageable);

    Author findAllByFirstName(String firstName);

    Set<Author> findByIdIn(List<Long> ids);

}
