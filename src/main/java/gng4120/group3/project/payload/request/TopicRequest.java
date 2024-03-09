package gng4120.group3.project.payload.request;

import gng4120.group3.project.models.forum.ETopic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

public class TopicRequest {
    @NotBlank
    private ETopic name;

    @NotBlank
    private String userId;

    @Size(max = 50)
    private String title;

    @Size(max = 2000)
    private String description;

    public ETopic getName() {
        return name;
    }

    public void setName(ETopic name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}