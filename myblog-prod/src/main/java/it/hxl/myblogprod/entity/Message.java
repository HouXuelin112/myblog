package it.hxl.myblogprod.entity;

import java.util.Date;
import java.util.List;

/**
 * 留言类
 */
public class Message {
    private int id;
    private Users user;
    private String messageContent;
    private int parentId;
    private Date issueDate;
    private Users toUser;
    private List<Message> childMessages;

    public List<Message> getChildMessages() {
        return childMessages;
    }

    public void setChildMessages(List<Message> childMessages) {
        this.childMessages = childMessages;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user=" + user +
                ", messageContent='" + messageContent + '\'' +
                ", parentId=" + parentId +
                ", issueDate=" + issueDate +
                ", toUser=" + toUser +
                '}';
    }

    public Users getToUser() {
        return toUser;
    }

    public void setToUser(Users toUser) {
        this.toUser = toUser;
    }

    public Message(int id, Users user, String messageContent, int parentId, Date issueDate, Users toUser) {
        this.id = id;
        this.user = user;
        this.messageContent = messageContent;
        this.parentId = parentId;
        this.issueDate = issueDate;
        this.toUser = toUser;
    }

    public Message() {
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

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
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

    public Message(int id, Users user, String messageContent, int parentId, Date issueDate) {
        this.id = id;
        this.user = user;
        this.messageContent = messageContent;
        this.parentId = parentId;
        this.issueDate = issueDate;
    }
}
