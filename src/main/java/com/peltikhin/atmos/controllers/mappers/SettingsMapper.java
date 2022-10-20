package com.peltikhin.atmos.controllers.mappers;

import com.peltikhin.atmos.controllers.dto.SettingsDto;
import com.peltikhin.atmos.jpa.models.Settings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface SettingsMapper {
    @Mapping(source = "user.id", target = "userId")
    SettingsDto toDto(Settings settings);
}
