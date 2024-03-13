package gng4120.group3.project.controllers.api;

import gng4120.group3.project.database.repository.forum.TopicRepository;
import gng4120.group3.project.database.repository.account.UserRepository;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/forum")
public class ForumAPIController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/asyncTopicsList")
    public String getAsyncTopicsList(Model model) {
        // Logic to fetch the list of topics asynchronously
        // Return the Thymeleaf template name that represents the content for the topics list
        // Logic to fetch the list of topic IDs asynchronously
        List<String> topicIds = topicRepository.findAllTopicIds();

        // Add the list of topic IDs as a session attribute
        model.addAttribute("topicIds", topicIds);

        System.out.println("Test1: " + topicIds.toString());
        return "fragments/forum/topics/topicList"; // Thymeleaf template name
    }

    @GetMapping("/asyncTopics/{id}")
    public String getAsyncTopic(@PathVariable String id, Model model) {
        // Logic to fetch additional content for the topic with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the topic
        model.addAttribute("topic", topicRepository.findById(id));

        System.out.println("Test2: " + id);
        return "fragments/forum/topics/topic"; // Thymeleaf template name
    }
}
