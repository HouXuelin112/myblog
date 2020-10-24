package it.hxl.myblogadmin1.entity;

import lombok.*;
import sun.misc.BASE64Encoder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
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
    private String displayBase64;
    private String description;

    private void setDisplayBase64(byte[] displayImage) {
        this.displayBase64 = new BASE64Encoder().encode(displayImage);
    }

    public void setDisplayImage(byte[] displayImage) {
        this.displayImage = displayImage;
        setDisplayBase64(displayImage);
    }


}
