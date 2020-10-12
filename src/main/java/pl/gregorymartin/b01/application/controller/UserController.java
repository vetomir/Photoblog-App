package pl.gregorymartin.b01.application.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.core.mapping.model.PostInListReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostReadModel;
import pl.gregorymartin.b01.core.mapping.model.PostWriteModel;
import pl.gregorymartin.b01.core.model.Post;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.model.User;
import pl.gregorymartin.b01.security.repository.RoleRepository;
import pl.gregorymartin.b01.security.repository.UserRepository;
import pl.gregorymartin.b01.security.service.UserAdd;
import pl.gregorymartin.b01.security.service.UserGet;

import java.net.URI;
import java.util.List;

@RestController("/api")
class UserController {
    private UserGet userGet;
    private UserAdd userAdd;

    public UserController(final UserGet userGet, final UserAdd userAdd) {
        this.userGet = userGet;
        this.userAdd = userAdd;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserReadModel>> getUsers(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(userGet.getUsers(pageNumber, sortDirection, sortByVariable));
    }
/*
    @GetMapping("/users")
    public ResponseEntity<PostReadModel> getSingleUser(@RequestParam long id) {
        return ResponseEntity.ok(userGet.getUserDto(id));
    }*/

    @PostMapping("/users")
    public ResponseEntity<UserReadModel> addUser(@RequestBody UserWriteModel user) {
        UserReadModel result = userAdd.registerUser(user);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
