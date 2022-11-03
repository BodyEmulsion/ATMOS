package com.peltikhin.atmos.services.mappers;

import com.peltikhin.atmos.services.dto.UserDto;
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
