package ru.luckyhunterzzz.springcourse.constants;

public class Constants {
    public static final String SELECT_ALL = "SELECT * FROM spring_alishev.persons";
    public static final String SELECT_ALL_WHERE_ID = "SELECT * FROM spring_alishev.persons WHERE id = ?";
    public static final String SELECT_ALL_WHERE_EMAIL = "SELECT * FROM spring_alishev.persons WHERE email = ?";
    public static final String INSERT_INTO_PERSONS = "INSERT INTO spring_alishev.persons(name, age, email, address) VALUES(?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE spring_alishev.persons SET name = ?, age = ?, email = ?, address = ? WHERE id = ?";
    public static final String DELETE = "DELETE FROM spring_alishev.persons WHERE id = ?";
}
