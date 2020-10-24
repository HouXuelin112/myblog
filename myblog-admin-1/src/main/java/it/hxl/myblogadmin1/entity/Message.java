package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 留言类
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Message implements Serializable {
    private int id;
    private Users user;
    private String messageContent;
    private int parentId;
    private Date issueDate;
    private Users toUser;
    private List<Message> childMessages;

}
