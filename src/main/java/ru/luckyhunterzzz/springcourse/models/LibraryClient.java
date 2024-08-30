package ru.luckyhunterzzz.springcourse.models;

import jakarta.validation.constraints.*;

import java.util.List;

public class LibraryClient {
    private int id;
    @NotEmpty(message = "Не может быть пустым")
    @Pattern(regexp = "([A-Za-zА-Яа-яЁё]+)\\s([A-Za-zА-Яа-яЁё]+)(\\s([A-Za-zА-Яа-яЁё]+))",
            message = "ФИО должно быть в формате: Фамилия, Имя, Отчество")
    private String fullName;
    @NotEmpty(message = "Не может быть пустым")
    @Min(value = 1930, message = "Год должен быть >= 1930")
    @Max(value = 2024, message = "Год должен быть <= 2024")
    private String birthYear;
    private List<LibraryBook> bookList;

    public LibraryClient() {
    }

    public LibraryClient(String fullName, String birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LibraryBook> getBookList() {
        return bookList;
    }

    public void setBookList(List<LibraryBook> bookList) {
        this.bookList = bookList;
    }
}
