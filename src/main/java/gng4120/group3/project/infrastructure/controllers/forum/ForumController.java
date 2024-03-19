package gng4120.group3.project.infrastructure.controllers.forum;

import gng4120.group3.project.payload.request.PostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forum")
public class ForumController {

    @RequestMapping({"", "/"})
    public String forumsPage() {
        return "pages/forum/forum";
    }

    @RequestMapping({"/{topic}/edit/","/{topic}/edit"})
    public String forumEditTopic(@PathVariable String topic, Model model) {

        return "pages/forum/topic/actions/editTopic";
    }

    @RequestMapping({"/{topic}/", "/{topic}"})
    public String forumTopic(@PathVariable String topic, Model model) {
        model.addAttribute("topicId", topic);
        return "pages/forum/topic/topic";
    }

    @RequestMapping({"/new/", "/new"})
    public String forumNewTopic(Model model) {

        return "pages/forum/topic/actions/newTopic";
    }

    @RequestMapping({"/{topic}/{post}/edit/", "/{topic}/{post}/edit"})
    public String forumEditPost(@PathVariable String topic, @PathVariable String post, Model model) {
        model.addAttribute("topicId", topic);
        model.addAttribute("postId", post);
        return "pages/forum/post/actions/editPost";
    }

    @RequestMapping({"/{topic}/{post}/", "/{topic}/{post}"})
    public String forumTopicPost(@PathVariable String topic, @PathVariable String post, Model model) {
        model.addAttribute("topicId", topic);
        model.addAttribute("postId", post);
        return "pages/forum/post/post";
    }

    @RequestMapping({"/{topic}/new/", "/{topic}/new"})
    public String forumNewPost(@PathVariable String topic, Model model) {
        model.addAttribute("topicId", topic);
        model.addAttribute("postRequest", new PostRequest());
        return "pages/forum/post/actions/newPost";
    }
}
