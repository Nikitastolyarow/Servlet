package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
@Repository
public class PostRepository implements PostRepositoryStubImpl {
    private final ConcurrentHashMap<Long, Post> posts;
    private final AtomicLong count = new AtomicLong();

    public PostRepository() {
        posts = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long id = count.getAndIncrement();
            post.setId(id);
            posts.put(id, post);
        } else if (posts.containsKey(post.getId())) {
            posts.put(post.getId(), post);
        } else {
            throw new NotFoundException("Post with id " + post.getId() + " not found");
        }
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);

    }
}
