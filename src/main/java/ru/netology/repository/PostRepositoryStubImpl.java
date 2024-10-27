package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepositoryStubImpl {
    List<Post> all();

    Optional<Post> getById(long id);

    Post save(Post post);

    public void removeById(long id);

}