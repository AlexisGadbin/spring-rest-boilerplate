package fr.gadbin.springrestboilerplate.application.service;

import org.springframework.stereotype.Service;

import fr.gadbin.springrestboilerplate.application.exception.WrongMailFormatException;
import fr.gadbin.springrestboilerplate.application.exception.WrongPasswordFormatException;
import fr.gadbin.springrestboilerplate.application.port.in.ValidateCredentialsUseCase;

@Service
public class ValidateCredentialsService implements ValidateCredentialsUseCase {

    private static final String MAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+.com$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+_=\\[\\]{};:'\"\\\\|,.<>/?]).{12,255}$";

    @Override
    public void checkMailFormat(String email) {
        if (!email.matches(MAIL_REGEX)) {
            throw new WrongMailFormatException();
        }
    }

    @Override
    public void checkPasswordStrength(String password) {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new WrongPasswordFormatException();
        }
    }
}
