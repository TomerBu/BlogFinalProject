package edu.tomerbu.blogfinalproject.service;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import java.util.Collection;

public interface PostService {
    //add a post: get a request dto ->
    // save the entity
    // return a response dto

    ResponsePostDto addPost(CreatePostDto dto);

    Collection<ResponsePostDto> getAll();

    //get post by id:
    ResponsePostDto getPostById(long id);

    ResponsePostDto updatePostById(long id, CreatePostDto dto);

}
