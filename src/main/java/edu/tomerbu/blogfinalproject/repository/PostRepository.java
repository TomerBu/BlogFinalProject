package edu.tomerbu.blogfinalproject.repository;

import edu.tomerbu.blogfinalproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}
