package edu.tomerbu.blogfinalproject.service;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import edu.tomerbu.blogfinalproject.entity.Post;
import edu.tomerbu.blogfinalproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

        //List<Post> (Entity)
        var all = postRepository.findAll();


        return all.stream().map(m->modelMapper.map(m, ResponsePostDto.class)).toList();
    }
}
