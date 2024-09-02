package ru.luckyhunterzzz.springcourse.constants;

public class Constants {
    public static final String SELECT_ALL = "SELECT * FROM spring_alishev.persons";
    public static final String SELECT_ALL_WHERE_ID = "SELECT * FROM spring_alishev.persons WHERE id = ?";
    public static final String SELECT_ALL_WHERE_EMAIL = "SELECT * FROM spring_alishev.persons WHERE email = ?";
    public static final String INSERT_INTO_PERSONS = "INSERT INTO spring_alishev.persons(name, age, email, address) VALUES(?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE spring_alishev.persons SET name = ?, age = ?, email = ?, address = ? WHERE id = ?";
    public static final String DELETE = "DELETE FROM spring_alishev.persons WHERE id = ?";

    public static final String SELECT_ALL_FROM_BOOK = "SELECT * FROM project1.library_book";
    public static final String SELECT_ALL_FROM_BOOK_ID = "SELECT * FROM project1.library_book WHERE id = ?";
    public static final String SELECT_CLIENT_FROM_BOOK_WITH_JOIN_BY_ID = "SELECT project1.library_client.* " +
            "FROM project1.library_book " +
            "JOIN project1.library_client " +
            "ON project1.library_book.library_client_id = project1.library_client.id " +
            "WHERE project1.library_book.id = ?";
    public static final String INSERT_INTO_NEW_BOOK = "INSERT INTO project1.library_book(name, author, year) VALUES(?, ?, ?)";
    public static final String UPDATE_BOOK = "UPDATE project1.library_book SET name = ?, author = ?,year = ? WHERE id = ?";
    public static final String DELETE_BOOK_BY_ID = "DELETE FROM project1.library_book WHERE id = ?";
    public static final String UPDATE_BOOK_FREE_FROM_CLIENT = "UPDATE project1.library_book SET library_client_id = NULL WHERE id = ?";
    public static final String UPDATE_BOOK_SET_NEW_CLIENT = "UPDATE project1.library_book SET library_client_id = ? WHERE id = ?";
    public static final String SELECT_ALL_FROM_CLIENT = "SELECT * FROM project1.library_client";
    public static final String SELECT_BOOK_WITH_JOIN_BOOK_CLIENT_BY_ID = "SELECT * FROM project1.library_book " +
            "JOIN project1.library_client " +
            "ON project1.library_book.library_client_id = project1.library_client.id " +
            "WHERE project1.library_book.library_client_id = ?";
    public static final String SELECT_CLIENT_BY_ID = "SELECT * FROM project1.library_client WHERE id = ?";
    public static final String SELECT_CLIENT_BY_FULLNAME = "SELECT * FROM project1.library_client WHERE full_name = ?";
    public static final String INSERT_INTO_NEW_CLIENT = "INSERT INTO project1.library_client(full_name, birth_year) VALUES(?, ?)";
    public static final String UPDATE_CLIENT = "UPDATE project1.library_client SET full_name = ?, birth_year = ? WHERE id = ?;";
    public static final String DELETE_CLIENT_BY_ID = "DELETE FROM project1.library_client WHERE id = ?";
}
