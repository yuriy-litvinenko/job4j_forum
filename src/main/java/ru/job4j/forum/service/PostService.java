package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostStoreMem;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    private final PostStoreMem postStore;

    public PostService(PostStoreMem postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAll() {
        return postStore.getAll();
    }

    public void save(Post post) {
        postStore.save(post);
    }

    public Post getById(int id) {
        Post post = postStore.getById(id);
        if (post == null) {
            throw new NoSuchElementException();
        }
        return post;
    }
}
