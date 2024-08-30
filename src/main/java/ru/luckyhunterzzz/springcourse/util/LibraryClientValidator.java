package ru.luckyhunterzzz.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.luckyhunterzzz.springcourse.dao.LibraryClientDAO;
import ru.luckyhunterzzz.springcourse.models.LibraryClient;

@Component
public class LibraryClientValidator implements Validator {
    private final LibraryClientDAO libraryClientDAO;

    @Autowired
    public LibraryClientValidator(LibraryClientDAO libraryClientDAO) {
        this.libraryClientDAO = libraryClientDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LibraryClient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LibraryClient libraryClient = (LibraryClient) target;
        if (libraryClientDAO.show(libraryClient.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Пользователь с таким ФИО уже существует");
        }
    }
}
