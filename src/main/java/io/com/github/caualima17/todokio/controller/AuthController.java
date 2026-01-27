package io.com.github.caualima17.todokio.controller;

import io.com.github.caualima17.todokio.service.AuthService;
import io.com.github.caualima17.todokio.transfer.AuthenticationDTO;
import io.com.github.caualima17.todokio.transfer.RegisterUserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        return ResponseEntity.ok(authService.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterUserDTO data) {
        authService.register(data);
        return ResponseEntity.ok().build();
    }
}
