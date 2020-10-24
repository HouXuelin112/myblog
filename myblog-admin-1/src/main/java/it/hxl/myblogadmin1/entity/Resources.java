package it.hxl.myblogadmin1.entity;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resources {
    private int id;
    private String url;
    private String description;
}
