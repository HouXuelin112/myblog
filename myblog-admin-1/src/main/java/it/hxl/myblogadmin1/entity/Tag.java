package it.hxl.myblogadmin1.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Tag implements Serializable {
    private int id;
    private String tagName;
    private String tagDescription;
}
