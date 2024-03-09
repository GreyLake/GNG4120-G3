package gng4120.group3.project.controllers.api;

import gng4120.group3.project.database.repository.forum.TopicRepository;
import gng4120.group3.project.database.repository.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/forum")
public class ForumAPIController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository forumRepository;

}
