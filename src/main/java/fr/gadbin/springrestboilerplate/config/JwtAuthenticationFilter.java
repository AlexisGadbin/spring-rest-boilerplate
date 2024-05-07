package fr.gadbin.springrestboilerplate.config;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.gadbin.springrestboilerplate.adapter.out.persistence.entity.UserEntity;
import fr.gadbin.springrestboilerplate.adapter.out.persistence.mapper.UserMapper;
import fr.gadbin.springrestboilerplate.adapter.out.persistence.repository.UserRepository;
import fr.gadbin.springrestboilerplate.application.exception.UserNotFoundException;
import fr.gadbin.springrestboilerplate.application.port.in.JwtUtilsUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtilsUseCase jwtUtilsUseCase;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtUtilsUseCase.extractUserName(jwt);
        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);

            connectUser(request, jwt, user);
        }
        filterChain.doFilter(request, response);

    }

    private void connectUser(HttpServletRequest request, String jwt, UserEntity user) {
        if (jwtUtilsUseCase.isTokenValid(jwt, this.userMapper.toDto(user))) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }
    }
}
