package ru.job4j.forum.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserStoreMem;

import java.util.List;
import java.util.Optional;

public class UserService implements UserDetailsService {
    private final UserStoreMem userStore;

    public UserService(UserStoreMem userStore) {
        this.userStore = userStore;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
    }

    public List<User> getAll() {
        return userStore.getAll();
    }

    public boolean create(User user) {
        return userStore.create(user);
    }

    public Optional<User> findByUsername(String username) {
        return userStore.findByUsername(username);
    }
}
