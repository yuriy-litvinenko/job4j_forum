package ru.job4j.forum.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
