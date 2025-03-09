package com.clozex.carsharingapp.service.impl;

import com.clozex.carsharingapp.model.Role;
import com.clozex.carsharingapp.repository.user.RoleRepository;
import com.clozex.carsharingapp.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(Role.RoleName roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() ->
                        new EntityNotFoundException("Role not found"));
    }
}
