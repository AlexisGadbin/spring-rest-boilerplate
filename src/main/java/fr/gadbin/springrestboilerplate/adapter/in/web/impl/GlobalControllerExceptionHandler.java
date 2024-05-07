package fr.gadbin.springrestboilerplate.adapter.in.web.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.gadbin.springrestboilerplate.application.exception.UserNotFoundException;
import fr.gadbin.springrestboilerplate.application.exception.WrongCredentialsException;
import fr.gadbin.springrestboilerplate.application.exception.WrongMailFormatException;
import fr.gadbin.springrestboilerplate.application.exception.WrongPasswordFormatException;
import fr.gadbin.springrestboilerplate.application.model.response.ErrorResponse;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongCredentialsException.class)
    public ErrorResponse handleWrongCredentialsException() {
        return ErrorResponse.builder()
                .name("WrongCredentials")
                .message("Les identifiants sont incorrects")
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException() {
        return ErrorResponse.builder()
                .name("UserNotFound")
                .message("L'utilisateur n'existe pas")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongMailFormatException.class)
    public ErrorResponse handleWrongMailFormatException() {
        return ErrorResponse.builder()
                .name("WrongMailFormat")
                .message("Le format de l'adresse mail est incorrect")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongPasswordFormatException.class)
    public ErrorResponse handleWrongPasswordFormatException() {
        return ErrorResponse.builder()
                .name("WrongPasswordFormat")
                .message("Le format du mot de passe est incorrect")
                .build();
    }
}
