package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    private final PostRepository postStore;

    public PostService(PostRepository postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        postStore.findAll().forEach(rsl::add);
        return rsl;
    }

    public void save(Post post) {
        postStore.save(post);
    }

    public Post getById(int id) {
        return postStore.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
