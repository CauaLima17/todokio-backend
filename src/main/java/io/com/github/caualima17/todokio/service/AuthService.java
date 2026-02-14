package io.com.github.caualima17.todokio.service;

import io.com.github.caualima17.todokio.config.jwt.JwtTokenProvider;
import io.com.github.caualima17.todokio.model.User;
import io.com.github.caualima17.todokio.repository.UserRepository;
import io.com.github.caualima17.todokio.transfer.AuthenticateUserRequestDTO;
import io.com.github.caualima17.todokio.transfer.AuthenticateUserResponseDTO;
import io.com.github.caualima17.todokio.transfer.RegisterUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthenticateUserResponseDTO login(AuthenticateUserRequestDTO data) {
        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
        var auth = authenticationManager.authenticate(userAuthToken);

        User user = Objects.requireNonNull((User) auth.getPrincipal());
        String token = jwtTokenProvider.generateToken(user);

        return AuthenticateUserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .token(token)
                .build();
    }

    public void register(RegisterUserRequestDTO data) {
        try {
            userRepository.findByEmail(data.getEmail()).ifPresent((u) -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um usuário registrado com esse email.");
            });

            User user = data.fromDtoToEntity();
            userRepository.save(user);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro desconhecido ao tentar registrar-se.");
        }
    }
}
