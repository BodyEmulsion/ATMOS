package com.peltikhin.atmos.controllers.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlockDto {
    private Long id;
    private Date timeStart;
    private Date timeEnd;
    private Long projectId;
}
