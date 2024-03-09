package gng4120.group3.project.infrastructure.services.forum;

import gng4120.group3.project.models.forum.Comment;
import gng4120.group3.project.models.forum.Post;

import java.util.List;
import java.util.Set;

public interface CommentService {

    Post findOne(String id);

    List<Comment> findAll();

    Set<Comment> findRecent();

    Set<Comment> findAllByOrderByCreationDateDesc();

    Set<Comment> findByUserId(String userId);

    Set<Comment> findByTopic(String commentId);

    void save(Comment comment);

    void delete(String id);

    void delete(Comment comment);

}
