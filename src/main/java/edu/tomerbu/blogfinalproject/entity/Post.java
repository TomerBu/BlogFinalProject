package edu.tomerbu.blogfinalproject.entity;

import edu.tomerbu.blogfinalproject.dto.CreatePostDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder

@Getter
@Setter

//@ToString

@Entity

//Unique Constraints:
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Post {

    @Id
    @GeneratedValue
    private Long id = null;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String content;

    public Post(Long id, CreatePostDto dto) {
        this.id = id;
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.content = dto.getContent();
    }
}
