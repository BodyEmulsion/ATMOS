package com.peltikhin.atmos.services.mappers;

import com.peltikhin.atmos.services.dto.BlockDto;
import com.peltikhin.atmos.jpa.models.Block;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BlockMapper {
    @Mapping(source = "project.id", target = "projectId")
    BlockDto toDto(Block block);
}
