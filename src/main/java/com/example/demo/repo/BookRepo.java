package com.example.demo.repo;

import com.example.demo.domian.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BookRepo extends CrudRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findAllByBookName(String name, Pageable pageable);

//    @Query( value = "select * from person p " +
//            "join person_addresses b " +
//            "   on p.id = b.person_id " +
//            "join address c " +
//            "   on c.id = b.addresses_id " +
//            "where c.zip = :zip", nativeQuery = true)
//    Iterable<Person> getPeopleWithZip(@Param("zip") String zip);


    @Query(value = "select * from tb_book b " +
            "join book_author ba " +
            "   on b.id = ba.book_id " +
            "join tb_author a " +
            "   on a.id = ba.author_id " +
            "where a.first_name = :firstName", nativeQuery = true)
    Page<Book> findByAuthorName(@Param("firstName") String firstName, Pageable pageable);
}
