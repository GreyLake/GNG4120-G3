package gng4120.group3.project.database.repository.forum;

import gng4120.group3.project.models.forum.Comment;
import gng4120.group3.project.models.forum.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    Optional<Comment> findById(String id);

    boolean existsById(String id);
}
