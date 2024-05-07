package fr.gadbin.springrestboilerplate.application.port.out;

import fr.gadbin.springrestboilerplate.application.model.User;

public interface AuthenticationPort {
    User authenticate(String email, String password);
}
