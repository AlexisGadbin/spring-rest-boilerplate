package fr.gadbin.springrestboilerplate.application.model;

import fr.gadbin.springrestboilerplate.application.model.enumeration.Role;

public record User(Long id, String email, String name, String password, Role role) {

}
