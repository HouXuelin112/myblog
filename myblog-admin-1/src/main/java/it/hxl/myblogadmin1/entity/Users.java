package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Users implements Serializable {

    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickName;
    private byte[] head;
    private Date registerDate;
    private Date lastAccessTime;
    private int status;

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
