package hello.controllers;

import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setProductService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/user/list", "/user"})
    public String listUsers(Model model){
        model.addAttribute("users", userService.loadAllUser());
        return "user/list";
    }
}
