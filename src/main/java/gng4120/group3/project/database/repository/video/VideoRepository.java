package gng4120.group3.project.database.repository.video;

import gng4120.group3.project.models.forum.Topic;
import gng4120.group3.project.models.video.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends MongoRepository<Video, String> {

    @Query(value = "{}", fields = "{ '_id' : 1 }")
    List<Video> findAllTopicIds();

    Optional<Video> findById(String id);

    boolean existsById(String id);

    boolean existsByTitle(String title);
}
