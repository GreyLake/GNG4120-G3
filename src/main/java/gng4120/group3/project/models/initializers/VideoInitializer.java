package gng4120.group3.project.models.initializers;

import gng4120.group3.project.database.MongoConfig;
import gng4120.group3.project.database.repository.forum.TopicRepository;
import gng4120.group3.project.database.repository.video.VideoRepository;
import gng4120.group3.project.models.forum.ETopic;
import gng4120.group3.project.models.forum.Topic;
import gng4120.group3.project.models.video.EVideo;
import gng4120.group3.project.models.video.Video;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoInitializer {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    private MongoConfig mongoConfig;

    @PostConstruct
    public void init() {
        // Iterate over each enum value and save the role if it doesn't exist
        if(mongoConfig.isMongoDBAvailable()) {
            for (EVideo videoName : EVideo.values()) {
                saveRoleIfNotExists(videoName);
            }
        }
    }

    private void saveRoleIfNotExists(EVideo eVideo) {
        // Check if role exists
        if (!videoRepository.existsByTitle(eVideo.getTitle())) {
            // Save roles to the database using the repository
            Video video = new Video(eVideo.getTitle(), eVideo.getDescription(), eVideo.getLink());
            videoRepository.save(video);
        }
    }
}
