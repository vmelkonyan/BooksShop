package com.example.demo;

import com.example.demo.domian.Author;
import com.example.demo.domian.Book;
import com.example.demo.domian.User;
import com.example.demo.domian.UserRole;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@RequiredArgsConstructor
@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final AuthorService authorService;
    private final BookService bookService;


    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) {
        createUsers();
        createAuthors();
//        createBooks();
    }

    @Transactional
    public void createBooks() {


        Book theLastOfTheMohicans = new Book("The Last of the Mohicans", 777, LocalDate.of(1826, 1, 8), 777);
        Book archOfTriumph = new Book("Arch of Triumph", 777, LocalDate.of(1945, 1, 8), 777);
        Book viperInTheFist = new Book("Viper in the Fist", 777, LocalDate.of(1948, 1, 8), 777);
        Book orthodoxy = new Book("Orthodoxy", 777, LocalDate.of(1908, 1, 8), 777);

        theLastOfTheMohicans.setAuthorSet(Collections.singleton(authorService.findAllByFirstName("James")));
        archOfTriumph.setAuthorSet(Collections.singleton(authorService.findAllByFirstName("Erich")));
        viperInTheFist.setAuthorSet(Collections.singleton(authorService.findAllByFirstName("Herve")));
        orthodoxy.setAuthorSet(Collections.singleton(authorService.findAllByFirstName("Gilbert")));

        bookService.saveAll(Arrays.asList(theLastOfTheMohicans, archOfTriumph, viperInTheFist, orthodoxy));

    }

    @Transactional
    public void createAuthors(){
        Author jamesFenimoreCooper = new Author("James", "Fenimore Cooper");
        Author erichMariaRemarque = new Author("Erich", "Maria Remarque");
        Author herveBazin = new Author("Herve", "Bazin");
        Author gilbertKeithChesterton = new Author("Gilbert", "Keith Chesterton");

        authorService.saveAll(Arrays.asList(jamesFenimoreCooper, erichMariaRemarque, herveBazin, gilbertKeithChesterton));
    }

    private void createUsers(){
        User userAdmin = userRepo.findByUsername("admin");
        if (userAdmin == null) {
            User user = new User("admin", passwordEncoder.encode("admin"));
            user.setUserRoles(Collections.singleton(UserRole.MANAGER));
            user.setActive(true);
            userRepo.save(user);
        }

        User employee = userRepo.findByUsername("user");
        if (employee == null) {
            User user = new User("user", passwordEncoder.encode("user"));
            user.setUserRoles(Collections.singleton(UserRole.EMPLOYEE));
            user.setActive(true);
            userRepo.save(user);
        }
    }


}
