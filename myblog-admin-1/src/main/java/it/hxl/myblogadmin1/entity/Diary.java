package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Diary implements Serializable {
    private int id;
    private String diaryContent;
    private byte[] image;
    private String title;
    private Date issueDate;
}
