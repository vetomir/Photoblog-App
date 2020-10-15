package pl.gregorymartin.b01.application.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.b01.security.mapping.model.RoleReadModel;
import pl.gregorymartin.b01.security.mapping.model.RoleWriteModel;
import pl.gregorymartin.b01.security.mapping.model.UserReadModel;
import pl.gregorymartin.b01.security.mapping.model.UserWriteModel;
import pl.gregorymartin.b01.security.service.role.RoleAdd;
import pl.gregorymartin.b01.security.service.role.RoleModify;
import pl.gregorymartin.b01.security.service.user.UserAdd;
import pl.gregorymartin.b01.security.service.user.UserGet;
import pl.gregorymartin.b01.security.service.user.UserModify;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {
    private UserGet userGet;
    private UserAdd userAdd;
    private UserModify userModify;
    private RoleAdd roleAdd;
    private RoleModify roleModify;

    public UserController(final UserGet userGet, final UserAdd userAdd, final UserModify userModify, final RoleAdd roleAdd, final RoleModify roleModify) {
        this.userGet = userGet;
        this.userAdd = userAdd;
        this.userModify = userModify;
        this.roleAdd = roleAdd;
        this.roleModify = roleModify;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserReadModel>> getUsers(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(userGet.getUsers(pageNumber, sortDirection, sortByVariable));
    }

    @PostMapping
    public ResponseEntity<UserReadModel> createUser(@RequestBody UserWriteModel user) {
        UserReadModel result = userAdd.registerUser(user);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PatchMapping
    public ResponseEntity<UserReadModel> updateUser(@RequestBody UserWriteModel user) {
        UserReadModel result = userModify.changeUserEmail(user);
        /*UserReadModel result2 = userModify.changeUserPassword(user);*/
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam long id) {
        userModify.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //Role

    @PostMapping("/roles")
    public ResponseEntity<RoleReadModel> createRole(@RequestBody RoleWriteModel role) {
        RoleReadModel result = roleAdd.addRole(role);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/roles")
    public ResponseEntity deleteRole(@RequestParam long id) {
        roleModify.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
