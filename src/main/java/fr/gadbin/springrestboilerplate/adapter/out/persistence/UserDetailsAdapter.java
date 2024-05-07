package fr.gadbin.springrestboilerplate.adapter.out.persistence;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import fr.gadbin.springrestboilerplate.adapter.out.persistence.repository.UserRepository;
import fr.gadbin.springrestboilerplate.application.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

}
