package pl.gregorymartin.b01.view.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.service.user.UserAdd;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
class RegisterController {
    private UserAdd userAdd;

    public RegisterController(final UserAdd userAdd) {
        this.userAdd = userAdd;
    }

    @GetMapping("/signup")
    String createUser(
            Model model,
            Authentication authentication
    ){
        if(authentication != null){
            return "redirect:/";
        }

        model.addAttribute("userWriteModel", new UserWriteModel());
        return "register";
    }
    @PostMapping("/register")
    String createUser(
            Model model,
            @Valid UserWriteModel userDto,
            BindingResult bindingResult
    ) {
        if(bindingResult.hasErrors()){
            return "register";
        }

        try{
            userAdd.registerUser(userDto);
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "register";
        }

        model.addAttribute("user", new User());
        model.addAttribute("userWriteModel", new UserWriteModel());
        return "redirect:/login";
    }

}
