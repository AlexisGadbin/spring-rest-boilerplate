package fr.gadbin.springrestboilerplate.adapter.in.web;

import org.springframework.http.ResponseEntity;

import fr.gadbin.springrestboilerplate.application.model.request.SignInRequest;
import fr.gadbin.springrestboilerplate.application.model.response.JwtAuthenticationResponse;

public interface AuthenticationController {
    ResponseEntity<JwtAuthenticationResponse> authenticate(SignInRequest request);
}
