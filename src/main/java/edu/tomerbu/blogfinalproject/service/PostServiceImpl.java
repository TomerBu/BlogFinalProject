package edu.tomerbu.blogfinalproject.service;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import edu.tomerbu.blogfinalproject.entity.Post;
import edu.tomerbu.blogfinalproject.error.ResourceNotFoundException;
import edu.tomerbu.blogfinalproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    //props
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public ResponsePostDto addPost(CreatePostDto dto) {

        //dto -> Entity
        var entity = modelMapper.map(dto, Post.class);
        //entity -> save
        var saved = postRepository.save(entity);
        //return entity -> response dto
        return modelMapper.map(saved, ResponsePostDto.class);
    }

    @Override
    public Collection<ResponsePostDto> getAll() {
        var all = postRepository.findAll();
        return all.stream().map(m -> modelMapper.map(m, ResponsePostDto.class)).toList();
    }

    @Override
    public ResponsePostDto getPostById(long id) {
        return modelMapper.map(
                getPostEntityByIdOrElseThrow(id),
                ResponsePostDto.class
        );
    }

    private Post getPostEntityByIdOrElseThrow(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
    }

    @Override
    public ResponsePostDto updatePostById(long id, CreatePostDto dto) {
        //get the post by id or throw not found:
        var post = getPostEntityByIdOrElseThrow(id);

        //update all the post fields using the dto:
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());

        //save the changes (UPDATE)
        var saved = postRepository.save(post);

        //return the mapping:
        return modelMapper.map(saved, ResponsePostDto.class);
    }


}
