package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Link implements Serializable {
    private int id;
    private String name;
    private String icon; //对应网站图标的base64编码
    private String description;
    private int status;
    private String addr;
}
