package gng4120.group3.project.infrastructure.services.forum.impl;

import gng4120.group3.project.infrastructure.services.forum.TopicService;
import gng4120.group3.project.models.forum.ETopic;
import gng4120.group3.project.models.forum.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Override
    public List<Topic> findAll() {
        return null;
    }

    @Override
    public Topic findOne(String id) {
        return null;
    }

    @Override
    public Topic findByName(ETopic name) {
        return null;
    }

    @Override
    public Topic save(Topic section) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(Topic section) {

    }
}
