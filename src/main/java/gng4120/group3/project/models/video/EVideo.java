package gng4120.group3.project.models.video;

public enum EVideo {
    VIDEO_1("Navigating Loans And Borrowing",
            "Moo Money - Learn All About Loans And Borrowing",
            "io3FeOGhS_Y"),
    VIDEO_2(
            "Introduction To Taxes",
            "Moo Money - Learn All About Taxes",
            "KrpvhRVFuEg"),

    VIDEO_3(
            "Budgeting Basics",
            "Moo Money - Learn All About Budgeting",
            "RbQQdx5G3Fk"),

    VIDEO_4(
            "Decoding The Stock Market",
            "Moo Money - Learn All About Stocks",
            "5Ww3hlvkofk");

    private final String title;
    private final String description;
    private final String link;

    EVideo(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public  String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getLink() {
        return link;
    }
    public String getName() {
        return this.name();
    }
}
