package ru.luckyhunterzzz.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.luckyhunterzzz.springcourse.constants.Constants;
import ru.luckyhunterzzz.springcourse.models.LibraryBook;
import ru.luckyhunterzzz.springcourse.models.LibraryClient;

import java.util.List;
import java.util.Optional;

import static ru.luckyhunterzzz.springcourse.constants.Constants.*;

@Component
public class LibraryBookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryBookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LibraryBook> index() {
        return jdbcTemplate.query(SELECT_ALL_FROM_BOOK,
                new BeanPropertyRowMapper<>(LibraryBook.class));
    }

    public Optional<LibraryBook> show(int id) {

        Optional<LibraryBook> libraryBook = jdbcTemplate.query(
                        SELECT_ALL_FROM_BOOK_ID,
                    new BeanPropertyRowMapper<>(LibraryBook.class),
                    id)
            .stream()
            .findFirst();

        return libraryBook;
    }

    public Optional<LibraryClient> getLibraryClient(int id) {
        return jdbcTemplate.query(SELECT_CLIENT_FROM_BOOK_WITH_JOIN_BY_ID,
                new BeanPropertyRowMapper<>(LibraryClient.class),
                id)
                .stream()
                .findFirst();
    }

    public void save(LibraryBook libraryBook) {
        jdbcTemplate.update(INSERT_INTO_NEW_BOOK,
                libraryBook.getName(),
                libraryBook.getAuthor(),
                libraryBook.getYear()
        );
    }

    public void update(int id, LibraryBook updatedLibraryBook) {
        jdbcTemplate.update(
                UPDATE_BOOK,
                updatedLibraryBook.getName(),
                updatedLibraryBook.getAuthor(),
                updatedLibraryBook.getYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_BOOK_BY_ID, id);
    }

    public void deleteClient(int id) {
        jdbcTemplate.update(UPDATE_BOOK_FREE_FROM_CLIENT,
                id);
    }

    public void assignClient(int bookId, int clientId) {
        String sql = UPDATE_BOOK_SET_NEW_CLIENT;
        jdbcTemplate.update(sql, clientId, bookId);
    }
}
