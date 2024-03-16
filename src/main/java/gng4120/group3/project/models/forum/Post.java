package gng4120.group3.project.models.forum;

import gng4120.group3.project.payload.request.PostRequest;
import gng4120.group3.project.payload.request.SignupRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    @NotBlank
    private String userId;

    private List<String> commentIds;

    @Size(max = 50)
    private String title;

    @Size(max = 2000)
    private String content;

    private int views;

    @NotBlank
    @CreatedDate
    private Date creationDate;

    @NotBlank
    @LastModifiedDate
    private Date lastUpdateDate;

    private boolean closed;

    public Post() {  }

    public Post(PostRequest request) {
        this.userId = request.getUserId();
        this.title = request.getTitle();
        this.content = request.getContent();
        this.commentIds = Collections.emptyList();
        this.creationDate = new Date();
        this.lastUpdateDate = this.creationDate;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}