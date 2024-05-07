package fr.gadbin.springrestboilerplate.application.port.in;

import fr.gadbin.springrestboilerplate.application.model.User;

public interface JwtUtilsUseCase {
    String extractUserName(String token);

    String generateToken(User user);

    boolean isTokenValid(String token, User user);
}
