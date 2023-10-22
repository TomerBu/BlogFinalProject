package edu.tomerbu.blogfinalproject.service;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import edu.tomerbu.blogfinalproject.dto.DeletePostResponseDto;
import edu.tomerbu.blogfinalproject.dto.ResponsePostDto;
import edu.tomerbu.blogfinalproject.entity.Post;
import edu.tomerbu.blogfinalproject.error.ResourceNotFoundException;
import edu.tomerbu.blogfinalproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public Collection<ResponsePostDto> getPage(int pageNo, int pageSize) {

        //page request: defines the page size and page number:
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        //SELECT the page from the repository: (SELECT * FROM Post LIMIT 10 SKIP 20)
        Page<Post> page = postRepository.findAll(pageable);

        //convert the page to List<Post>
        List<Post> postList = page.getContent();

        return postList.stream()
                .map(p-> modelMapper.map(p, ResponsePostDto.class))
                .toList();
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

    @Override
    public DeletePostResponseDto deletePostById(long id) {
        //get the post:
        var post = postRepository.findById(id);

        //delete:
        postRepository.deleteById(id);

        //return true if existed before deletion:
        return DeletePostResponseDto.builder()
                .deleted(post.isPresent())
                .post(modelMapper.map(post, ResponsePostDto.class))
                .build();
    }
}
