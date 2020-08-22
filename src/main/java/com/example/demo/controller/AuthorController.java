package com.example.demo.controller;

import com.example.demo.constatns.KeyConstants;
import com.example.demo.domian.Author;
import com.example.demo.domian.User;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping(KeyConstants.AUTHOR_LIST_KEY)
public class AuthorController {

    private final AuthorService authorService;


    @GetMapping()
    public String list(Model model, @PageableDefault Pageable pageable) {
        Page<Author> authorPage = authorService.findAll(pageable);
        model.addAttribute("page", authorPage);
        model.addAttribute("url", KeyConstants.AUTHOR_LIST_KEY);
        return KeyConstants.AUTHOR_LIST_VIEW;
    }

    @PostMapping
    public String createAuthor(@RequestParam String firstName,
                               @RequestParam String lastName,
                               Model model, @PageableDefault Pageable pageable) {

        Author author = new Author(firstName, lastName);

        authorService.save(author);
        log.info("Author added successfully {}", author.getId());
        Page<Author> authorPage = authorService.findAll(pageable);
        model.addAttribute("page", authorPage);
        model.addAttribute("url", KeyConstants.AUTHOR_LIST_KEY);
        return KeyConstants.AUTHOR_LIST_VIEW;
    }
}
