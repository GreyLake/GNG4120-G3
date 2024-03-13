package gng4120.group3.project.infrastructure.controllers.forum;

import gng4120.group3.project.infrastructure.services.forum.CommentService;
import gng4120.group3.project.infrastructure.services.forum.PostService;
import gng4120.group3.project.infrastructure.services.forum.TopicService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    @RequestMapping({"", "/"})
    public String forumsPage( Model model) {
        return "pages/forum/forum";
    }

    @RequestMapping("/{topic}")
    public String forumPage(@PathVariable String topic, Model model) {

        return "pages/forum/topic";
    }

    @RequestMapping("/{topic}/{post}")
    public String forumPage(@PathVariable String topic, @PathVariable Long post, Model model) {

        return "pages/forum/post";
    }
}
