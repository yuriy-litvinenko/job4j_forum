package ru.job4j.forum.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceRep implements UserDetailsService {
    private final UserRepository userRep;
    private final AuthorityRepository authorityRep;
    private final PasswordEncoder passwordEncoder;

    public UserServiceRep(UserRepository userRep, AuthorityRepository authorityRep, PasswordEncoder passwordEncoder) {
        this.userRep = userRep;
        this.authorityRep = authorityRep;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        userRep.findAll().forEach(result::add);
        return result;
    }

    public boolean create(User user) {
        boolean result = false;
        Optional<User> foundUser = findByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addAuthority(authorityRep.findByName("ROLE_USER").get());
            userRep.save(user);
            result = true;
        }
        return result;
    }

    public Optional<User> findByUsername(String username) {
        return userRep.findByUsername(username);
    }
}
