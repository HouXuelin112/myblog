package it.hxl.myblogprod.entity;

import java.util.Date;
import java.util.List;

public class Comments {
    private int id;
    private Users user;
    private Blog blog;
    private String commentContent;
    private int parentId;
    private Date issueDate;
    private String childHtml;
    private Users toUser;
    private List<Comments> childComments;

    public List<Comments> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<Comments> childComments) {
        this.childComments = childComments;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", user=" + user +
                ", blog=" + blog +
                ", commentContent='" + commentContent + '\'' +
                ", parentId=" + parentId +
                ", issueDate=" + issueDate +
                ", childHtml='" + childHtml + '\'' +
                ", toUser=" + toUser +
                '}';
    }

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
    }

    public Comments(int id, Users user, Blog blog, String commentContent, int parentId, Date issueDate, String childHtml, Users toUser) {
        this.id = id;
        this.user = user;
        this.blog = blog;
        this.commentContent = commentContent;
        this.parentId = parentId;
        this.issueDate = issueDate;
        this.childHtml = childHtml;
        this.toUser = toUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getChildHtml() {
        return childHtml;
    }

    public void setChildHtml(String childHtml) {
        this.childHtml = childHtml;
    }

    public Comments(int id, Users user, Blog blog, String commentContent, int parentId, Date issueDate, String childHtml) {
        this.id = id;
        this.user = user;
        this.blog = blog;
        this.commentContent = commentContent;
        this.parentId = parentId;
        this.issueDate = issueDate;
        this.childHtml = childHtml;
    }

    public Comments() {
    }
}
