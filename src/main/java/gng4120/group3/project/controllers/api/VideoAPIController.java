package gng4120.group3.project.controllers.api;

import gng4120.group3.project.database.repository.video.VideoRepository;
import gng4120.group3.project.infrastructure.exceptions.ErrorCode;
import gng4120.group3.project.models.video.Video;
import gng4120.group3.project.payload.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/video")
public class VideoAPIController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/asyncVideoPage")
    public String getAsyncVideoList(Model model) {
        // Logic to fetch the list of topics asynchronously
        // Return the Thymeleaf template name that represents the content for the topics list
        // Logic to fetch the list of topic IDs asynchronously

        List<String> videoIds = videoRepository.findAllTopicIds().stream().map(Video::getId).toList();

        // Add the list of topic IDs as a session attribute
        model.addAttribute("videoIds", videoIds);

        return "fragments/video/videoList"; // Thymeleaf template name
    }

    @GetMapping("/asyncVideo/{id}")
    public String getAsyncVideo(@PathVariable String id, Model model) {
        // Logic to fetch additional content for the topic with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the topic

        Video video = videoRepository.findById(id).orElse(new Video());
        model.addAttribute("video", video);

        return "fragments/video/videoItem"; // Thymeleaf template name
    }

    @GetMapping("/asyncVideoPage/{id}")
    public String getAsyncTopicPage(@PathVariable String id, Model model) throws IOException {
        // Logic to fetch additional content for the topic with the given ID asynchronously
        // Return the Thymeleaf template name that represents the content for the topic
        Optional<Video> videoOptional = videoRepository.findById(id);
        if(videoOptional.isEmpty()) {

            HttpStatus status = HttpStatus.BAD_REQUEST;

            ErrorResponse exceptionResponse = new ErrorResponse();
            exceptionResponse.setStatus(status.toString());
            exceptionResponse.setStatusCode(status.value());
            exceptionResponse.setMessage("Cannot Find Video Resource");
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
        Video video = videoOptional.get();
        model.addAttribute("video", video);

        return "fragments/video/video"; // Thymeleaf template name
    }
}
