package ryukyu.suwako.spring.boot.fuwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ryukyu.suwako.spring.boot.fuwa.entity.User;
import ryukyu.suwako.spring.boot.fuwa.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.readUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String showUser(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("user", userService.readUser(id));
        return "user";
    }

    @GetMapping("/new")
    public String createUserForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "createUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("user", userService.readUser(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable int id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("{id}")
    public String deleteUserForm(@PathVariable int id) {
        userService.removeUser(userService.readUser(id));
        return "redirect:/users";
    }
}
