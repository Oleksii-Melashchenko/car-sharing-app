package com.clozex.carsharingapp.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.clozex.carsharingapp.configuration.MapperConfig;
import com.clozex.carsharingapp.dto.user.UserRegisterResponseDto;
import com.clozex.carsharingapp.dto.user.UserRegistrationRequestDto;
import com.clozex.carsharingapp.dto.user.UserResponseDto;
import com.clozex.carsharingapp.model.Role;
import com.clozex.carsharingapp.model.User;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = IGNORE)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

    @Mapping(target = "roles", source = "roles")
    UserResponseDto toDto(User user);

    UserRegisterResponseDto toRegisterDto(User user);

    default Set<String> map(Set<Role> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(role -> role.getName()
                        .toString().replace("ROLE_", ""))
                .collect(Collectors.toSet());
    }
}
