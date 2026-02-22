package com.register.backend.rest.auth;

import com.register.backend.db.entities.UserEntity;
import com.register.backend.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbUserDetailsService implements UserDetailsService  {

    private final UserRepository userRepository;

    @Autowired
    public DbUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEnt = userRepository.findByUsername(username);
        if (userEnt == null) {
            throw new UsernameNotFoundException("User not found");
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userEnt.getRole().name());

        return new User(
                userEnt.getUsername(),
                userEnt.getPassword(),
                List.of(authority)
        );
    }

}
