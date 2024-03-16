package gng4120.group3.project.controllers.api;

import gng4120.group3.project.database.repository.forum.CommentRepository;
import gng4120.group3.project.database.repository.forum.PostRepository;
import gng4120.group3.project.database.repository.forum.TopicRepository;
import gng4120.group3.project.database.repository.account.UserRepository;
import gng4120.group3.project.infrastructure.exceptions.ErrorCode;
import gng4120.group3.project.models.forum.Post;
import gng4120.group3.project.models.forum.Topic;
import gng4120.group3.project.payload.response.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/forum")
public class ForumAPIController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/asyncForumPage")
    public String getAsyncTopicPage(Model model) {
        // Logic to fetch additional content for the topic with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the topic
        int numPosts = postRepository.findAllPostIds().size();

        model.addAttribute("numPosts", numPosts);

        return "fragments/forum/forum"; // Thymeleaf template name
    }

    @GetMapping("/asyncTopicsList")
    public String getAsyncTopicsList(Model model) {
        // Logic to fetch the list of topics asynchronously
        // Return the Thymeleaf template name that represents the content for the topics list
        // Logic to fetch the list of topic IDs asynchronously
        List<String> topicIds = topicRepository.findAllTopicIds().stream().map(Topic::getId).toList();

        // Add the list of topic IDs as a session attribute
        model.addAttribute("topicIds", topicIds);

        return "fragments/forum/topics/topicList"; // Thymeleaf template name
    }

    @GetMapping("/asyncTopics/{id}")
    public String getAsyncTopic(@PathVariable String id, Model model) {
        // Logic to fetch additional content for the topic with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the topic
        Topic topic = topicRepository.findById(id).orElse(new Topic());
        model.addAttribute("topic", topic);

        return "fragments/forum/topics/topicItem"; // Thymeleaf template name
    }

    @GetMapping("/asyncTopicPage/{id}")
    public String getAsyncTopicPage(@PathVariable String id, Model model) throws IOException {
        // Logic to fetch additional content for the topic with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the topic
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isEmpty()) {

            HttpStatus status = HttpStatus.BAD_REQUEST;

            ErrorResponse exceptionResponse = new ErrorResponse();
            exceptionResponse.setStatus(status.toString());
            exceptionResponse.setStatusCode(status.value());
            exceptionResponse.setMessage("Cannot Find Topic Resource");
            exceptionResponse.setPath(id);
            exceptionResponse.setTimestamp(new Date());

            if (status.series() == HttpStatus.Series.CLIENT_ERROR) {
                exceptionResponse.setErrorCode(ErrorCode.CLIENT_ERROR);
            } else if (status.series() == HttpStatus.Series.SERVER_ERROR) {
                exceptionResponse.setErrorCode(ErrorCode.INTERNAL_ERROR);
            }

            model.addAttribute("exception", exceptionResponse);

            return "fragments/error/error";
        }
        Topic topic = topicOptional.get();
        model.addAttribute("topic", topic);

        return "fragments/forum/topics/topic"; // Thymeleaf template name
    }

    @GetMapping("/asyncPostsList/{id}")
    public String getAsyncPostsList(@PathVariable String id, Model model) {
        // Logic to fetch the list of posts asynchronously
        // Return the Thymeleaf template name that represents the content for the posts list
        // Logic to fetch the list of post IDs asynchronously
        Topic topic = topicRepository.findById(id).orElse(new Topic());
        model.addAttribute("topicId",
                topic.getId()!=null ? topic.getId() : "NotFound");
        model.addAttribute("postIds",
                topic.getPostIds()!=null ? topic.getPostIds() : Collections.emptyList());

        return "fragments/forum/posts/postList"; // Thymeleaf template name
    }

    @GetMapping("/asyncPosts/{id}")
    public String getAsyncPost(@PathVariable String id, Model model) {
        // Logic to fetch additional content for the post with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the post
        Post post = postRepository.findById(id).orElse(new Post());
        model.addAttribute("post", post);

        return "fragments/forum/posts/postItem"; // Thymeleaf template name
    }

    @GetMapping("/asyncPostPage/{id}")
    public String getAsyncPostPage(@PathVariable String id, Model model) {
        // Logic to fetch additional content for the post with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the post
        Post post = postRepository.findById(id).orElse(new Post());
        model.addAttribute("post", post);

        return "fragments/forum/posts/post"; // Thymeleaf template name
    }

}
