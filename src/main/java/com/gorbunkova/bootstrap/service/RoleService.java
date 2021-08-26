package com.gorbunkova.bootstrap.service;


import com.gorbunkova.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

    List<Role> getRolesList();

    Role getRoleByName(String s);
}
