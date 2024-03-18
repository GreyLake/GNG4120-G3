package gng4120.group3.project.infrastructure.controllers.video;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/videos", "/video"})
public class VideoController {

    @RequestMapping({"", "/"})
    public String videosPage() {
        return "pages/video/videos";
    }

    @RequestMapping({"/{video}/", "/{video}"})
    public String videoPage(@PathVariable String video, Model model) {
        model.addAttribute("videoId", video);
        return "pages/video/video/video";
    }

    @RequestMapping({"/new/", "/new"})
    public String VideoNewVideo(Model model) {
        return "pages/video/actions/newVideo";
    }

    @RequestMapping({"/{video}/del/", "/{video}/del"})
    public String videoDelPage(@PathVariable String video, Model model) {
        model.addAttribute("videoId", video);
        return "pages/video/actions/delVideo";
    }

}

