package it.hxl.myblogadmin1.entity;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Permissions {
    private int id;
    private String perms;
    private String description;
}
