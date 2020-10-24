package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Admin implements Serializable {

    private int id;
    private String adminName;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private String nickName;
    private byte[] head;
    private String salt;

}
