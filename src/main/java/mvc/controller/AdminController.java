package mvc.controller;

import mvc.model.Role;
import mvc.model.User;
import mvc.service.RoleService;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String startPage() {
        return "redirect:/login";
    }

    @GetMapping(value = "/admin")
    public String getUsers(Model model) {
        model.addAttribute("allUsers", userService.listUsers());
        return "admin";
    }

    @GetMapping(value = "/admin/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.listRoles());
        return "add";
    }

    @PostMapping(value = "/admin/add-user")
    public String newUser(@ModelAttribute("user") User user,
                          @RequestParam(required = false) String[] selectedRole) {
        Set<Role> roleSet = new HashSet<>();
        if (selectedRole != null) {
            for (String role : selectedRole) {
                roleSet.add(roleService.getRoleByName(role));
            }
        } else {
            roleSet.add(roleService.getRoleByName("ROLE_USER"));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.listRoles());
        return "edit";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "roleStatus") String[] roleStatus) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : roleStatus) {
            roleSet.add(roleService.getRoleByName(roles));
        }
        user.setRoles(roleSet);
        userService.editUser(user);
        return "redirect:/admin";
    }
}