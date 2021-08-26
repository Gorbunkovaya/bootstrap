package com.gorbunkova.bootstrap.dao;


import com.gorbunkova.bootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RoleRepository extends JpaRepository <Role, Long> {
    @Query("select role from Role role join fetch role.users where role.role = :role")
    Role getRoleByName(@Param("role") String role);
}
