package it.hxl.myblogadmin1.entity;

public class Tag {
    private int id;
    private String tagName;
    private String tagDescription;

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", tagDescription='" + tagDescription + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Tag(int id, String tagName, String tagDescription) {
        this.id = id;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
    }
}
