package ru.luckyhunterzzz.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.luckyhunterzzz.springcourse.models.LibraryBook;
import ru.luckyhunterzzz.springcourse.models.LibraryClient;

import java.util.List;

@Component
public class LibraryBookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryBookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LibraryBook> index() {
        return jdbcTemplate.query("SELECT * FROM project1.library_book",
                new BeanPropertyRowMapper<>(LibraryBook.class));
    }

    public LibraryBook show(int id) {

        LibraryBook libraryBook = jdbcTemplate.query(
                    "SELECT * FROM project1.library_book WHERE id = ?",
                    new BeanPropertyRowMapper<>(LibraryBook.class),
                    id)
            .stream()
            .findFirst()
            .orElse(null);

         //Если книга найдена, можно получить клиента
        if (libraryBook != null) {
            // Получаем клиента по library_client_id из книги
            LibraryClient libraryClient = jdbcTemplate.query(
                            "SELECT * FROM project1.library_client WHERE id = ?",
                            new BeanPropertyRowMapper<>(LibraryClient.class),
                            libraryBook.getLibraryClientId())
                    .stream()
                    .findFirst()
                    .orElse(null);
//
//            // Устанавливаем клиента в книгу
            libraryBook.setClientName(libraryClient.getFullName());
        }

        return libraryBook;
    }

    public void save(LibraryBook libraryBook) {
        jdbcTemplate.update("INSERT INTO project1.library_book(name, author, year) VALUES(?, ?, ?)",
                libraryBook.getName(),
                libraryBook.getAuthor(),
                libraryBook.getYear()
        );
    }

    public void update(int id, LibraryBook updatedLibraryBook) {
        jdbcTemplate.update(
                "UPDATE project1.library_book SET name = ?, author = ?,year = ? WHERE id = ?",
                updatedLibraryBook.getName(),
                updatedLibraryBook.getAuthor(),
                updatedLibraryBook.getYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM project1.library_book WHERE id = ?", id);
    }

    public void deleteClient(int id) {
        jdbcTemplate.update("UPDATE project1.library_book SET library_client_id = 7 WHERE id = ?;",
                id);
    }

    public void assignClient(int bookId, int clientId) {
        String sql = "UPDATE project1.library_book SET library_client_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, clientId, bookId);
    }
}
