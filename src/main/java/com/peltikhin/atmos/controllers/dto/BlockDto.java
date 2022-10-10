package com.peltikhin.atmos.controllers.dto;

import com.peltikhin.atmos.jpa.models.Block;
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
    //TODO move it to mapper or somewhere else
    public BlockDto(Block block) {
        this.id = block.getId();
        this.timeStart = block.getTimeStart();
        this.timeEnd = block.getTimeEnd();
        this.projectId = block.getProject().getId();
    }
}
