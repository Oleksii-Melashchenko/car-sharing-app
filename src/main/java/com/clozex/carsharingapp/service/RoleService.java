package com.clozex.carsharingapp.service;

import com.clozex.carsharingapp.model.Role;

public interface RoleService {
    Role findByName(Role.RoleName roleName);
}
