package com.pdamjanovic.quizapp.security;


import com.pdamjanovic.quizapp.entity.User;
import com.pdamjanovic.quizapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
    Ovo je Spring Security-jev "ugovor" koji kaže:
    "kad neko pokuša da se uloguje sa nekim username-om,
    evo kako da nađeš tog korisnika u bazi."
     Spring Security ovo automatski poziva tokom procesa autentifikacije.
     */
@Service
public class CustomUserDetailsService implements UserDetailsService {

private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findByUsername(username)
                 .orElseThrow(()->new UsernameNotFoundException("Korisnik nije pronadjen: "+username));
                return new CustomUserDetails(user);
    }
}
