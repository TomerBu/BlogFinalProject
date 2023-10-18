package edu.tomerbu.blogfinalproject.controller;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import edu.tomerbu.blogfinalproject.entity.Post;
import edu.tomerbu.blogfinalproject.service.PostService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Collection<ResponsePostDto>> getAll(){
        var res = postService.getAll();
        return ResponseEntity.ok(res);
    }

    //add a post:
    @PostMapping
    public ResponseEntity<ResponsePostDto> addPost(@RequestBody @Valid CreatePostDto dto, UriComponentsBuilder uriBuilder){
        //1) tell the service to save the post
        var saved = postService.addPost(dto);


        //2) response uri:
        var uri = uriBuilder.path("/api/v1/posts/{id}").buildAndExpand(saved.getId());
        return ResponseEntity.created(uri.toUri()).body(saved);
    }

}
