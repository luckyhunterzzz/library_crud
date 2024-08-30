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
        return jdbcTemplate.query("SELECT * FROM project1.library_client",
                new BeanPropertyRowMapper<>(LibraryClient.class));
    }

    public LibraryClient show(int id) {
        List<LibraryBook> books = jdbcTemplate.query("SELECT * FROM project1.library_book " +
                        "JOIN project1.library_client " +
                        "ON project1.library_book.library_client_id = project1.library_client.id " +
                        "WHERE project1.library_book.library_client_id = ?",
                new BeanPropertyRowMapper<>(LibraryBook.class),
                id);
        LibraryClient libraryClient = jdbcTemplate.query("SELECT * FROM project1.library_client WHERE id = ?",
                        new BeanPropertyRowMapper<>(LibraryClient.class),
                        id)
                .stream()
                .findFirst()
                .orElse(null);

        if (books.isEmpty()) {
            libraryClient.setBookList(null);
            return libraryClient;
        } else {
            libraryClient.setBookList(books);
            return libraryClient;
        }
    }

    public Optional<LibraryClient> show(String fullName) {
        return jdbcTemplate.query("SELECT * FROM project1.library_client WHERE full_name = ?",
                new BeanPropertyRowMapper<>(LibraryClient.class),
                fullName)
                .stream()
                .findFirst();
    }

    public void save(LibraryClient libraryClient) {
        jdbcTemplate.update("INSERT INTO project1.library_client(full_name, birth_year) VALUES(?, ?)",
                libraryClient.getFullName(),
                libraryClient.getBirthYear()
        );
    }

    public void update(int id, LibraryClient updatedLibraryClient) {
        jdbcTemplate.update(
                "UPDATE project1.library_client SET full_name = ?, birth_year = ? WHERE id = ?",
                updatedLibraryClient.getFullName(),
                updatedLibraryClient.getBirthYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM project1.library_client WHERE id = ?", id);
    }

    public List<LibraryBook> setBook(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM project1.library_book " +
                        "JOIN project1.library_client " +
                        "ON project1.library_book.library_client_id = project1.library_client.id " +
                        "WHERE project1.library_book.library_client_id = ?",
                new BeanPropertyRowMapper<>(LibraryBook.class),
                id
        );
    }
}
