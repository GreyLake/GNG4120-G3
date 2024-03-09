package gng4120.group3.project.infrastructure.services.forum;

import gng4120.group3.project.models.forum.Post;

import java.util.List;
import java.util.Set;

public interface PostService {

    Post findOne(String id);

    List<Post> findAll();

    Set<Post> findRecent();

    Set<Post> findAllByOrderByCreationDateDesc();

    Set<Post> findByUserId(String userId);

    Set<Post> findByTopic(String topicId);

    void save(Post post);

    void delete(String id);

    void delete(Post post);

}
