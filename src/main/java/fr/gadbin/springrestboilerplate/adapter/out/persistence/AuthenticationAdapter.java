package fr.gadbin.springrestboilerplate.adapter.out.persistence;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import fr.gadbin.springrestboilerplate.adapter.out.persistence.entity.UserEntity;
import fr.gadbin.springrestboilerplate.adapter.out.persistence.mapper.UserMapper;
import fr.gadbin.springrestboilerplate.adapter.out.persistence.repository.UserRepository;
import fr.gadbin.springrestboilerplate.application.exception.UserNotFoundException;
import fr.gadbin.springrestboilerplate.application.exception.WrongCredentialsException;
import fr.gadbin.springrestboilerplate.application.model.User;
import fr.gadbin.springrestboilerplate.application.port.out.AuthenticationPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationAdapter implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User authenticate(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new WrongCredentialsException();
        }

        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return this.userMapper.toDto(user);
    }
}