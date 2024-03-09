package gng4120.group3.project.infrastructure.services.forum.impl;

import gng4120.group3.project.infrastructure.services.forum.CommentService;
import gng4120.group3.project.models.forum.Comment;
import gng4120.group3.project.models.forum.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Post findOne(String id) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Set<Comment> findRecent() {
        return null;
    }

    @Override
    public Set<Comment> findAllByOrderByCreationDateDesc() {
        return null;
    }

    @Override
    public Set<Comment> findByUserId(String userId) {
        return null;
    }

    @Override
    public Set<Comment> findByTopic(String commentId) {
        return null;
    }

    @Override
    public void save(Comment comment) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void delete(Comment comment) {

    }
}
