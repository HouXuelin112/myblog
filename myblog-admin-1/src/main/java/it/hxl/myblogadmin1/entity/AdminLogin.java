package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class AdminLogin implements Serializable {
    private int id;
    private int adminId;
    private String loginIp;
    private Date lastAccessTime;
    private int status;

}
