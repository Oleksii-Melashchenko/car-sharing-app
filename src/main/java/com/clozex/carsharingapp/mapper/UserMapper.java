package com.clozex.carsharingapp.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.clozex.carsharingapp.configuration.MapperConfig;
import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.model.Role;
import com.clozex.carsharingapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = IGNORE)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

    @Mapping(target = "role", source = "roles", qualifiedByName = "roleToString")
    UserResponseDto toDto(User user);

    UserRegisterResponseDto toRegisterDto(User user);

    @Named("roleToString")
    default String roleToString(Role role) {
        if (role == null) {
            return null;
        }
        return role.toString().replace("ROLE_", "");
    }

}
