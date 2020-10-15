package pl.gregorymartin.b01.application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.b01.security.model.User;

@RestController
class HelloController {
    @GetMapping("/test-hello")
    String hi(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        return user.getUsername();
    }
}
