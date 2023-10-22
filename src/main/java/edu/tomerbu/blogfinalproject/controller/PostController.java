package edu.tomerbu.blogfinalproject.controller;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.DeletePostResponseDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import edu.tomerbu.blogfinalproject.entity.Post;
import edu.tomerbu.blogfinalproject.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    //props:
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Collection<ResponsePostDto>> getAll() {
        var res = postService.getAll();
        return ResponseEntity.ok(res);
    }

    ///http://localhost:8080/api/v1/posts/page?pageSize=3&pageNo=1&sortBy=title&sortDir=desc
    @GetMapping("/page")
    public ResponseEntity<Collection<ResponsePostDto>> getPage(
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir) {
        var res = postService.getPage(pageNo, pageSize);
        return ResponseEntity.ok(res);
    }

    //add a post:
    @PostMapping
    public ResponseEntity<ResponsePostDto> addPost(@RequestBody @Valid CreatePostDto dto, UriComponentsBuilder uriBuilder) {
        //1) tell the service to save the post
        var saved = postService.addPost(dto);


        //2) response uri:
        var uri = uriBuilder.path("/api/v1/posts/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uri.toUri()).body(saved);
    }

    // /api/v1/posts/5
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePostDto> getPostById(@PathVariable @Valid @NotNull Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePostDto> updatePostById(
            @PathVariable @Valid @NotNull Long id,
            @RequestBody @Valid CreatePostDto dto) {
        return ResponseEntity.ok(postService.updatePostById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeletePostResponseDto> deletePostById(@PathVariable @Valid @NotNull Long id) {
        return ResponseEntity.ok(postService.deletePostById(id));
    }


}
