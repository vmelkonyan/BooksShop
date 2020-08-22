package com.example.demo.domian;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    @NonNull
    private String bookName;

    @Column(name = "page_count")
    @NonNull
    private Integer pageCount;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "published_date")
    @NonNull
    private LocalDate publishedDate;

    @Column(name = "book_cont", nullable = false)
    @NonNull
    private Integer availableBook;



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authorSet = new HashSet<>();

    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 1", nullable = false)
    private long version = 1L;
}