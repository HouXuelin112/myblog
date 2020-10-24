package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Comments implements Serializable {
    private int id;
    private Users user;
    private Blog blog;
    private String commentContent;
    private int parentId;
    private Date issueDate;
    private String childHtml;
    private Users toUser;
    private List<Comments> childComments;

}
