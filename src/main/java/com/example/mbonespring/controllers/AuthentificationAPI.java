package com.example.mbonespring.controllers;

import com.example.mbonespring.ClientDefault;
import com.example.mbonespring.MboneSpringApplication;
import com.example.mbonespring.models.dto.UserDTO;
import com.example.mbonespring.models.entities.UserEntity;
import com.example.mbonespring.services.AuthentificationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthentificationAPI {

    private final ClientDefault clientDefault;
   // @Bean

    private final AuthenticationManager authenticationManager;
    private static final String SECRET = "mypersonnalveryveryverysecretsecuritytokensecuritysauceforourangularproject";

    private static final Long TOKEN_VALIDITY = 3600 * 1000L;

    @PostConstruct
    private void initClient(){
        clientDefault.populateUserDatabase();
    }

    @GetMapping("/test")
    public String test(){
        return "test reussi";
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid AuthentificationRequest request){

        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
            final UserEntity user = (UserEntity) authenticate.getPrincipal();
            final String token = Jwts.builder().setSubject(authenticate.getName())
                    .claim("authorities", authenticate.getAuthorities()
                            .stream().map(GrantedAuthority::getAuthority)
                            .toList())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
            UserDTO userDTO = new UserDTO(user.getUsername(), token);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                    .body(userDTO);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}