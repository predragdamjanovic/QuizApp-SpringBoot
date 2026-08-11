package com.pdamjanovic.quizapp.security;

import com.pdamjanovic.quizapp.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /* Treba da vrati stvarne role
    Ovo mora da prođe kroz user.getRoles() i svaku Role pretvori u SimpleGrantedAuthority

     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
