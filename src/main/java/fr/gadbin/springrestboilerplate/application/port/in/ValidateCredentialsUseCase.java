package fr.gadbin.springrestboilerplate.application.port.in;

public interface ValidateCredentialsUseCase {
    void checkMailFormat(String email);

    void checkPasswordStrength(String password);
}
