package gng4120.group3.project.infrastructure.services.forum.impl;

import gng4120.group3.project.infrastructure.services.forum.PostService;
import gng4120.group3.project.models.forum.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public Post findOne(String id) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Set<Post> findRecent() {
        return null;
    }

    @Override
    public Set<Post> findAllByOrderByCreationDateDesc() {
        return null;
    }

    @Override
    public Set<Post> findByUserId(String userId) {
        return null;
    }

    @Override
    public Set<Post> findByTopic(String topicId) {
        return null;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(Post post) {

    }
}
