package gng4120.group3.project.models.initializers;


import gng4120.group3.project.database.MongoConfig;
import gng4120.group3.project.database.repository.forum.TopicRepository;
import gng4120.group3.project.models.forum.ETopic;
import gng4120.group3.project.models.forum.Topic;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TopicInitializer {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    private MongoConfig mongoConfig;

    @PostConstruct
    public void init() {
        // Iterate over each enum value and save the role if it doesn't exist
        if(mongoConfig.isMongoDBAvailable()) {
            for (ETopic topicName : ETopic.values()) {
                saveRoleIfNotExists(topicName);
            }
        }
    }

    private void saveRoleIfNotExists(ETopic topicName) {
        // Check if role exists
        Optional<Topic> existingTopic = topicRepository.findByName(topicName);
        if (existingTopic.isEmpty()) {
            // Save roles to the database using the repository
            Topic topic = new Topic(topicName);
            topicRepository.save(topic);
        }
    }
}

