package gng4120.group3.project.database.repository.forum;

import gng4120.group3.project.models.forum.ETopic;
import gng4120.group3.project.models.forum.Post;
import gng4120.group3.project.models.forum.Topic;
import gng4120.group3.project.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query(value = "{}", fields = "{ '_id' : 1 }")
    List<Post> findAllPostIds();

    Optional<Post> findById(String id);

    boolean existsById(String id);
}
