package xyz.leequix.mscescheduleapi.mscescheduleapi.model;

public class SubgroupPair {
    private String title;

    private String audience;

    public SubgroupPair(String title, String audience) {
        this.title = title;
        this.audience = audience;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
