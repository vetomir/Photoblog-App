package pl.gregorymartin.b01.application.controller;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.b01.application.service.post.PostAdd;
import pl.gregorymartin.b01.application.service.post.PostGet;
import pl.gregorymartin.b01.application.service.post.PostModify;
import pl.gregorymartin.b01.core.mapping.dto.PostDtoMapper;
import pl.gregorymartin.b01.core.mapping.dto.PostList;

import java.util.List;

@RestController
@RequestMapping(name = "/api")
class PostController {
    private PostGet postGet;
    private PostAdd postAdd;
    private PostModify postModify;

    public PostController(final PostGet postGet, final PostAdd postAdd, final PostModify postModify) {
        this.postGet = postGet;
        this.postAdd = postAdd;
        this.postModify = postModify;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostList>> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
                                                  /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        return ResponseEntity.ok(postGet.getPosts(pageNumber, sortDirection, sortByVariable));
    }
}
