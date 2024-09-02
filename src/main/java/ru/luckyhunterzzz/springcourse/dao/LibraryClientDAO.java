package ru.luckyhunterzzz.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.luckyhunterzzz.springcourse.models.LibraryBook;
import ru.luckyhunterzzz.springcourse.models.LibraryClient;

import java.util.List;
import java.util.Optional;

import static ru.luckyhunterzzz.springcourse.constants.Constants.*;

@Component
public class LibraryClientDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LibraryClient> index() {
        return jdbcTemplate.query(SELECT_ALL_FROM_CLIENT,
                new BeanPropertyRowMapper<>(LibraryClient.class));
    }

    public LibraryClient show(int id) {
        List<LibraryBook> books = jdbcTemplate.query(SELECT_BOOK_WITH_JOIN_BOOK_CLIENT_BY_ID,
                new BeanPropertyRowMapper<>(LibraryBook.class),
                id);

        LibraryClient libraryClient = jdbcTemplate.query(SELECT_CLIENT_BY_ID,
                        new BeanPropertyRowMapper<>(LibraryClient.class),
                        id)
                .stream()
                .findFirst()
                .orElse(null);

        libraryClient.setBookList(books);

        return libraryClient;
    }

    public Optional<LibraryClient> show(String fullName) {
        return jdbcTemplate.query(SELECT_CLIENT_BY_FULLNAME,
                new BeanPropertyRowMapper<>(LibraryClient.class),
                fullName)
                .stream()
                .findFirst();
    }

    public void save(LibraryClient libraryClient) {
        jdbcTemplate.update(INSERT_INTO_NEW_CLIENT,
                libraryClient.getFullName(),
                libraryClient.getBirthYear()
        );
    }

    public void update(int id, LibraryClient updatedLibraryClient) {
        jdbcTemplate.update(
                UPDATE_CLIENT,
                updatedLibraryClient.getFullName(),
                updatedLibraryClient.getBirthYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_CLIENT_BY_ID, id);
    }

    public List<LibraryBook> setBook(int id) {
        return jdbcTemplate.query(
                SELECT_BOOK_WITH_JOIN_BOOK_CLIENT_BY_ID,
                new BeanPropertyRowMapper<>(LibraryBook.class),
                id
        );
    }
}
