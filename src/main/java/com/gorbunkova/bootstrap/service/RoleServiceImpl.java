package com.gorbunkova.bootstrap.service;


import com.gorbunkova.bootstrap.dao.RoleRepository;
import com.gorbunkova.bootstrap.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getRolesList() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByName(String s) {
        return roleRepository.getRoleByName(s);
    }
}
