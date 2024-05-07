package fr.gadbin.springrestboilerplate.application.service;

import org.springframework.stereotype.Service;

import fr.gadbin.springrestboilerplate.application.model.User;
import fr.gadbin.springrestboilerplate.application.model.request.SignInRequest;
import fr.gadbin.springrestboilerplate.application.model.response.JwtAuthenticationResponse;
import fr.gadbin.springrestboilerplate.application.port.in.AuthenticationUseCase;
import fr.gadbin.springrestboilerplate.application.port.in.JwtUtilsUseCase;
import fr.gadbin.springrestboilerplate.application.port.in.ValidateCredentialsUseCase;
import fr.gadbin.springrestboilerplate.application.port.out.AuthenticationPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    private final ValidateCredentialsUseCase validateCredentialsUseCase;
    private final JwtUtilsUseCase jwtUtilsUseCase;
    private final AuthenticationPort authenticationPort;

    @Override
    public JwtAuthenticationResponse authenticate(SignInRequest request) {
        this.validateCredentialsUseCase.checkMailFormat(request.getEmail());

        User user = this.authenticationPort.authenticate(request.getEmail(), request.getPassword());

        String token = this.jwtUtilsUseCase.generateToken(user);

        return JwtAuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
