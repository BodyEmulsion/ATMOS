package com.peltikhin.atmos.controllers.mappers;

import com.peltikhin.atmos.controllers.dto.UserDto;
import com.peltikhin.atmos.jpa.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {
    UserDto toDto(User user);
}
