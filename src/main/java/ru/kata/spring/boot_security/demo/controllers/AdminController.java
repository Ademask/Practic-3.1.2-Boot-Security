package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.PeopleService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@Controller
public class AdminController {
    private final PeopleService peopleService;
    private final RoleService roleService;

    @Autowired
    public AdminController(PeopleService peopleService, RoleService roleService) {
        this.peopleService = peopleService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("users", peopleService.findAll());
        return "all";
    }

    @GetMapping("/admin/user")
    public String getUserPage(@ModelAttribute("user") User user, @RequestParam("id") int id,
                              Model model) {
        model.addAttribute("user", peopleService.findOne(id));
        return "user";
    }

    @GetMapping("/admin/new")
    public String getNewUserPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("role", roleService.getAll());
        return "new";
    }

    @PostMapping("/admin/new")
    public String createNewUser(@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        peopleService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String getEditUserPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", peopleService.findOne(id));
        model.addAttribute("role", roleService.getAll());
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        peopleService.update(user.getId(), user);
        return "redirect:/admin";
    }


    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") int id) {
        peopleService.delete(id);
        return "redirect:/admin";
    }
}

