package fr.gadbin.springrestboilerplate.adapter.in.web.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.gadbin.springrestboilerplate.adapter.in.web.AuthenticationController;
import fr.gadbin.springrestboilerplate.application.model.request.SignInRequest;
import fr.gadbin.springrestboilerplate.application.model.response.JwtAuthenticationResponse;
import fr.gadbin.springrestboilerplate.application.port.in.AuthenticationUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @Override
    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> authenticate(@RequestBody SignInRequest request) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationUseCase.authenticate(request);

        return ResponseEntity.ok(jwtAuthenticationResponse);
    }
}
