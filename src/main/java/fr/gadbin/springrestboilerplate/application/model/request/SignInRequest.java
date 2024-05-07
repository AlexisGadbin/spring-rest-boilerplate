package fr.gadbin.springrestboilerplate.application.model.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String email;
    private String password;
}
