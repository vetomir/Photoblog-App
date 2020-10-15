package pl.gregorymartin.b01.view.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.UserRepository;
import pl.gregorymartin.b01.security.service.user.UserAdd;


@Controller
class LoginController {

    private UserAdd userAdd;
    private UserRepository repository;

    //thymeleaf require
    public LoginController(final UserAdd userAdd, final UserRepository repository) {
        this.userAdd = userAdd;
        this.repository = repository;
    }

    @RequestMapping("/login")
    public String login(
            Authentication authentication
    ){
        if (authentication != null){
            return "redirect:/";
        }
        return "login";
    }
}
