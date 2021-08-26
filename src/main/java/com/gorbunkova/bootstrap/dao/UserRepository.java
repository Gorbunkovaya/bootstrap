package com.gorbunkova.bootstrap.dao;


import com.gorbunkova.bootstrap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository <User, Long> {
    @Query("select user from User user join fetch user.roles where user.login = :login")
    User getUserByName(@Param("login") String login);
}
