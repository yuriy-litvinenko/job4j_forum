package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStoreMem {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public PostStoreMem() {
        int index = id.incrementAndGet();
        Post post = Post.of(index, "Продаю машину ладу 01.", "В отличном состоянии");
        posts.put(index, post);
    }

    public List<Post> getAll() {
        return posts.values().stream().toList();
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(id.incrementAndGet());
            post.setCreated(LocalDateTime.now());
        }
        posts.put(post.getId(), post);
    }

    public Post getById(int id) {
        return posts.get(id);
    }
}
