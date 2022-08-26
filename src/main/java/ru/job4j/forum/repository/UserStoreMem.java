package ru.job4j.forum.repository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserStoreMem {
    private final Map<Integer, User> users = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();
    private final PasswordEncoder passwordEncoder;

    public UserStoreMem(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        int index = id.incrementAndGet();
        User user = User.of(index, "user", passwordEncoder.encode("123"));
        users.put(index, user);
    }

    public List<User> getAll() {
        return (List<User>) users.values();
    }

    public boolean create(User user) {
        boolean result = false;
        Optional<User> foundUser = findByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            int index = id.incrementAndGet();
            user.setId(index);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addAuthority(Authority.of(1, "ROLE_USER"));
            users.put(index, user);
            result = true;
        }
        return result;
    }

    public Optional<User> findByUsername(String username) {
        return users.values()
                .stream()
                .filter(f -> username.equals(f.getUsername()))
                .findFirst();
    }
}
