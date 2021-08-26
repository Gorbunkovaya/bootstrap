package com.gorbunkova.bootstrap.controller;

import com.gorbunkova.bootstrap.model.Role;
import com.gorbunkova.bootstrap.model.User;
import com.gorbunkova.bootstrap.service.RoleService;
import com.gorbunkova.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@Controller

public class UsersController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String getUser(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/admin")
    public String allUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getUsersList());
        model.addAttribute("roles", roleService.getRolesList());
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRolesList());
        return "new";
    }

    @PostMapping(value = "/admin/newuser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "rolesbox") String[] rolesBox) {
        Set<Role> roles = new HashSet<>();
        for (String rolesBoxes : rolesBox) {
            roles.add(roleService.getRoleByName(rolesBoxes));
        }
        user.setRoles(roles);
        userService.createUser(user);
        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "rolesbox") String[] rolesBox) {
        Set<Role> roles = new HashSet<>();
        for (String rolesBoxes : rolesBox) {
            roles.add(roleService.getRoleByName(rolesBoxes));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUserById(@RequestParam(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}

