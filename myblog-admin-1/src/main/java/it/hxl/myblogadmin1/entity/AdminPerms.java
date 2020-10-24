package it.hxl.myblogadmin1.entity;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminPerms {
    private int id;
    private int permsId;
    private int adminId;
}
