package ru.job4j.forum.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class Authority implements GrantedAuthority {
    private int id;
    private String name;

    public static Authority of(int id, String name) {
        Authority authority = new Authority();
        authority.id = id;
        authority.name = name;
        return authority;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority = (Authority) o;
        return id == authority.id && name.equals(authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
