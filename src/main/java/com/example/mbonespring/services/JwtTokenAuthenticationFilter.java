package com.example.mbonespring.services;

import com.example.mbonespring.models.interfaces.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    private static final String SECRET = "mypersonnalveryveryverysecretsecuritytokensecuritysauceforourangularproject";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer")){
            logger.debug("Pas d'entête authorization");
        }

        if(header == null || !header.startsWith("Bearer")){
            logger.debug("header null ou ne commence pas par bearer");
        }else{
            final String token = header.split(" ")[1].trim();

            final Claims claims =
//                    Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
                    Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token).getBody();
            if(claims.getExpiration().before(new Date())){
                logger.debug("Token expiré");
            }
            final String username = claims.getSubject();
            final UserDetails userDetails = userRepository.findByUsername(username);
            final UsernamePasswordAuthenticationToken

                    authentication = new UsernamePasswordAuthenticationToken
                    (
                            userDetails, null,
                            userDetails == null ?
                                    List.of() : userDetails.getAuthorities()
                    );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}