package org.launchcode.spaday.controllers;

import org.launchcode.spaday.data.UserData;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    @GetMapping("add")
    public String displayAddUserForm() {
        return "users/add";
    }

    @PostMapping("add")
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        if (user.getPassword().equals(verify)) {
            UserData.add(user);
            return "redirect:";
        }
        else {
            model.addAttribute("error", "The passwords do not match");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "users/add";
        }
    }

    @GetMapping
    public String displayAllUsers(Model model) {
        model.addAttribute("users", UserData.getAll());
        return "users/index";
    }

    @GetMapping("user/{userId}")
    public String displayUser(Model model, @PathVariable int userId) {
        User user = UserData.getById(userId);
        model.addAttribute("user", user);
        return "users/user";
    }
}
