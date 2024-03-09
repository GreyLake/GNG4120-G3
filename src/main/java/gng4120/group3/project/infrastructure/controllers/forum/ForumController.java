package gng4120.group3.project.infrastructure.controllers.forum;

import gng4120.group3.project.infrastructure.services.forum.CommentService;
import gng4120.group3.project.infrastructure.services.forum.PostService;
import gng4120.group3.project.infrastructure.services.forum.TopicService;
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

    @RequestMapping("/")
    public String forumsPage( Model model) {
        return "pages/forum/forums";
    }

    @RequestMapping("/{id}")
    public String forumPage(@PathVariable Long id, Model model) {

        return "pages/forum/forum";
    }
}
