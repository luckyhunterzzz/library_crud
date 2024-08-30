package ru.luckyhunterzzz.springcourse.models;


import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, max = 30, message = "Should be between 2 and 30 characters")
    private String name;
    @Min(value = 0, message = "Should be positive value")
    private int age;
    @NotEmpty(message = "Should not be empty")
    @Email(message = "Should be correct email")
    private String email;
    //Страна, Город, Индекс
    //Russia, Moscow, 142576
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Address should be: Country, City, ZIP (6 digits)")
    private String address;

    public Person() {

    }

    public Person(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
