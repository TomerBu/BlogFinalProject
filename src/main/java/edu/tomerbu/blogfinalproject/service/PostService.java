package edu.tomerbu.blogfinalproject.service;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.DeletePostResponseDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import java.util.Collection;

public interface PostService {
    //add a post: get a request dto ->
    // save the entity
    // return a response dto

    ResponsePostDto addPost(CreatePostDto dto);

    Collection<ResponsePostDto> getAll();

    Collection<ResponsePostDto> getPage(int pageNo, int pageSize);

    //get post by id:
    ResponsePostDto getPostById(long id);

    ResponsePostDto updatePostById(long id, CreatePostDto dto);

    //DELETE A Post By ID:
    DeletePostResponseDto deletePostById(long id);



}
