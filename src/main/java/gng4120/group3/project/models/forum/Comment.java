package gng4120.group3.project.models.forum;

import gng4120.group3.project.payload.request.CommentRequest;
import gng4120.group3.project.payload.request.SignupRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @NotBlank
    private String userId;

    private List<String> commentIds;

    @Size(max = 50)
    private String title;

    @Size(max = 2000)
    private String content;

    private boolean closed;

    public Comment() {}

    public Comment(CommentRequest request) {
        this.userId = request.getUserId();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.closed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
