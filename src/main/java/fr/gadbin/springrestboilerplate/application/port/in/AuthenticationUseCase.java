package fr.gadbin.springrestboilerplate.application.port.in;

import fr.gadbin.springrestboilerplate.application.model.request.SignInRequest;
import fr.gadbin.springrestboilerplate.application.model.response.JwtAuthenticationResponse;

public interface AuthenticationUseCase {
    JwtAuthenticationResponse authenticate(SignInRequest request);
}
