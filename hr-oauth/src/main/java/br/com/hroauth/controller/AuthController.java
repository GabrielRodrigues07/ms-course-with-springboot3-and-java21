package br.com.hroauth.controller;

import br.com.hroauth.config.ClientAuthValidator;
import br.com.hroauth.config.JwtUtil;
import br.com.hroauth.model.dto.Role;
import br.com.hroauth.model.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/oauth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClientAuthValidator clientAuthValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest, HttpServletRequest httpRequest) {

        if (!clientAuthValidator.isValid(httpRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid client credentials");
        }

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password));

        User user = (User) authenticate.getPrincipal();

        List<Role> roles = new ArrayList<>(user.getRoles());
        String token = jwtUtil.generateToken(user.getUsername(), roles);

        return ResponseEntity.ok(new AuthResponse(token));

    }

    record AuthRequest(String username, String password) {}

    record AuthResponse(String token) {}
}
