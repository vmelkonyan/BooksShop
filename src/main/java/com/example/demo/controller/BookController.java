package com.example.demo.controller;

import com.example.demo.constatns.KeyConstants;
import com.example.demo.domian.Author;
import com.example.demo.domian.Book;
import com.example.demo.domian.User;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(KeyConstants.BOOK_KEY)
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;


    @GetMapping
    public String list(@RequestParam(required = false, defaultValue = "") String search,
                       @RequestParam(required = false, defaultValue = "") String searchBy,
                       @PageableDefault Pageable pageable,
                       Model model) {
        checkBeforeShown(search, searchBy, pageable, model);
        return KeyConstants.BOOK_LIST;
    }

    @PostMapping
    public String createBook(@RequestParam String bookName,
                             @RequestParam Integer pageCount,
                             @RequestParam Integer availableBook,
                             @RequestParam(defaultValue = "2020-08-22") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedDate,
                             @RequestParam String authorsIds,
                             Model model, @PageableDefault Pageable pageable) {

        Book book = new Book(bookName, pageCount, publishedDate, availableBook);
        List<Long> lst = Arrays.stream(authorsIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        Set<Author> authors = authorService.findByIdIn(lst);
        authors.forEach(Author::increment);
        book.setAuthorSet(authors);
        bookService.save(book);
        log.info("Book added successfully {}", book.getId());
        checkBeforeShown("", null, pageable, model);
        return KeyConstants.BOOK_LIST;
    }

    private void checkBeforeShown(String search, String searchBy, @PageableDefault Pageable pageable, Model model) {
        Page<Book> bookList = bookService.find(search, searchBy, pageable);

        model.addAttribute("page", bookList);

        Iterable<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("search", search);
        model.addAttribute("url", KeyConstants.BOOK_KEY);
    }

    @GetMapping(path = KeyConstants.GET_BOOK_KEY + "/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return KeyConstants.BOOK_ITEM_VIEW;
    }

    @GetMapping(path = KeyConstants.BUY_BOOK_KEY + "/{id}")
    public String buyBook(@PathVariable Long id) {
        bookService.buyBookById(id);

        return KeyConstants.REDIRECT_KEY + KeyConstants.BOOK_KEY + KeyConstants.GET_BOOK_KEY + "/" + id;
    }

}
