package ru.luckyhunterzzz.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

public class LibraryBook {
    private int id;
    @NotEmpty(message = "Не может быть пустым")
    private String name;
    private String author;
    @Max(value = 2024, message = "Год должен быть <= 2024")
    private int year;

    private int libraryClientId;

    private String clientName;

    public LibraryBook(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public LibraryBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name + " by " + author + " (" + year + ")";
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getLibraryClientId() {
        return libraryClientId;
    }

    public void setLibraryClientId(int libraryClientId) {
        this.libraryClientId = libraryClientId;
    }
}
