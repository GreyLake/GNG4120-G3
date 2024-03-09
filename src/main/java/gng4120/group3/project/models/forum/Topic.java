package gng4120.group3.project.models.forum;

import gng4120.group3.project.payload.request.TopicRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "topics")
public class Topic {
    @Id
    private String id;

    @NotBlank
    private ETopic name;

    @NotBlank
    private String userId;

    private List<String> postIds;

    @Size(max = 50)
    private String title;

    @Size(max = 2000)
    private String description;

    private int posts;

    @NotBlank
    @CreatedDate
    private Date creationDate;

    @NotBlank
    @LastModifiedDate
    private Date lastUpdateDate;

    private boolean closed;

    public Topic() {}

    public Topic(ETopic name) {
        this.name = name;
    }

    public Topic(TopicRequest request) {
        this.userId = request.getUserId();
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.name = request.getName();
        this.closed = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<String> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<String> postIds) {
        this.postIds = postIds;
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

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
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