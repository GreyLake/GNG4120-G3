package gng4120.group3.project.models.forum;

public enum ETopic {
    TOPIC_GENERAL("Share experiences, give advice or ask questions regarding financial topics."),
    TOPIC_OTHER("Talk about sports, entertainment, music, movies, your favorite color, talk about anything.");

    private final String description;

    ETopic(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return this.name();
    }
}
