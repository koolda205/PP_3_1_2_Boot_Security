package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String showAdminPage(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/addNewUser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {

        model.addAttribute("users", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/findUsersById{id}")
    public String findUsersById(@PathVariable("id") Long id,
                                Model model) {

        model.addAttribute("user", userService.getUserById(id));

        if (userService.getUserById(id) == null)  {
            return "error-page";
        }
        return "user-info";
    }

    @GetMapping("/{id}/editUserById")
    public String editUsersById(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());

        if (userService.getUserById(id) == null) {
            return "error-page";
        }
        return "edit";
    }

    @PatchMapping("/editUser/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") Long id) {

        userService.updateUser(id, user);

        return "redirect:/admin";
    }

    @GetMapping("/deleteUserById")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {

        if (userService.getUserById(id) == null) {
            return "error-page";
        }
        userService.deleteUser(id);

        return "redirect:/admin";
    }

}