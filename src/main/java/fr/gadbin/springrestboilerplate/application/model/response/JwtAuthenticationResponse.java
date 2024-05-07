package fr.gadbin.springrestboilerplate.application.model.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class JwtAuthenticationResponse {
    private String token;
}
