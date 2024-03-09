package gng4120.group3.project.infrastructure.services.forum;

import gng4120.group3.project.models.forum.ETopic;
import gng4120.group3.project.models.forum.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> findAll();

    Topic findOne(String id);

    Topic findByName(ETopic name);

    Topic save(Topic section);

    void delete(int id);

    void delete(Topic section);
}
