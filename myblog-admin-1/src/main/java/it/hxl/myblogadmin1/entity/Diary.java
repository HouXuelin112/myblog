package it.hxl.myblogadmin1.entity;

import java.util.Arrays;
import java.util.Date;

public class Diary {
    private int id;
    private String diaryContent;
    private byte[] image;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", diaryContent='" + diaryContent + '\'' +
                ", image=" + Arrays.toString(image) +
                ", issueDate=" + issueDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Diary(int id, String diaryContent, byte[] image, String title, Date issueDate) {
        this.id = id;
        this.diaryContent = diaryContent;
        this.image = image;
        this.title = title;
        this.issueDate = issueDate;
    }

    public Diary() {
    }

    private Date issueDate;
}
