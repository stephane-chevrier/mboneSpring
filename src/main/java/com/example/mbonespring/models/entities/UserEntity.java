package com.example.mbonespring.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean accountNonExpired;

    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> userAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // parcours userAuthorities et transforme chaque élément en objet GrantedAuthority
        return userAuthorities.stream()
                .map(s -> (GrantedAuthority) () -> s)
                .toList();
        //le .collect permet d'arrêter et de convertir le stream pour pouvoir le retourner
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void addGrantedAuthoity(String s) {
    }

    public void setAccountNotExpired(boolean b) {
    }
}