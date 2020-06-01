package it.hxl.myblogprod.entity;

import java.util.Arrays;
import java.util.Date;

public class Blog {
    private int id;
    private String title;
    private String filename;
    private Admin admin; //Author_id
    private int isTop;
    private int isOriginal;
    private Tag tag; //tag_id
    private int visitCount;
    private int commentsCount;
    private byte[] displayImage;
    private Date issueDate;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blog(int id, String title, String filename, Admin admin, int isTop, int isOriginal, Tag tag, int visitCount, int commentsCount, byte[] displayImage, Date issueDate, String description) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.admin = admin;
        this.isTop = isTop;
        this.isOriginal = isOriginal;
        this.tag = tag;
        this.visitCount = visitCount;
        this.commentsCount = commentsCount;
        this.displayImage = displayImage;
        this.issueDate = issueDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filename='" + filename + '\'' +
                ", admin=" + admin +
                ", isTop=" + isTop +
                ", isOriginal=" + isOriginal +
                ", tag=" + tag +
                ", visitCount=" + visitCount +
                ", CommentsCount=" + commentsCount +
                ", displayImage=" + Arrays.toString(displayImage) +
                ", issueDate=" + issueDate +
                '}';
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Blog(int id, String title, String filename, Admin admin, int isTop, int isOriginal, Tag tag, int visitCount, int commentsCount, byte[] displayImage, Date issueDate) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.admin = admin;
        this.isTop = isTop;
        this.isOriginal = isOriginal;
        this.tag = tag;
        this.visitCount = visitCount;
        this.commentsCount = commentsCount;
        this.displayImage = displayImage;
        this.issueDate = issueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(int isOriginal) {
        this.isOriginal = isOriginal;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public byte[] getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(byte[] displayImage) {
        this.displayImage = displayImage;
    }

    public Blog() {
    }

    public Blog(int id, String title, String filename, Admin admin, int isTop, int isOriginal, Tag tag, int visitCount, int commentsCount, byte[] displayImage) {
        this.id = id;
        this.title = title;
        this.filename = filename;
        this.admin = admin;
        this.isTop = isTop;
        this.isOriginal = isOriginal;
        this.tag = tag;
        this.visitCount = visitCount;
        this.commentsCount = commentsCount;
        this.displayImage = displayImage;
    }
}
