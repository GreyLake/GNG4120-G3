package gng4120.group3.project.database.repository.forum;

import gng4120.group3.project.models.forum.ETopic;
import gng4120.group3.project.models.forum.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TopicRepository extends MongoRepository<Topic, String> {

    Optional<Topic> findByName(ETopic name);
    Optional<Topic> findById(String id);
    boolean existsById(String id);
}
