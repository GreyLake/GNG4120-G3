package gng4120.group3.project.database.repository.forum;

import gng4120.group3.project.models.forum.Post;
import gng4120.group3.project.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {
    Optional<Post> findById(String id);
    boolean existsById(String id);
}
