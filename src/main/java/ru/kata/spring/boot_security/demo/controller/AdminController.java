package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("create")
    public String createUserForm(User user, Model model) {
        model.addAttribute("roleList", userService.listRoles());
        return "create";
    }

    @PostMapping("create")
    public String createUser(User user) {
        List<String> listS = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
        List<Role> listR = userService.listByRole(listS);
        user.setRoles(listR);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", userService.listRoles());
        return "update";
    }

    @PostMapping("update")
    public String updateUser(User user) {
        List<String> listS = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
        List<Role> listR = userService.listByRole(listS);
        user.setRoles(listR);
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}