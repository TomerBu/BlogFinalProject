package edu.tomerbu.blogfinalproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ResponsePostDto {
    private long id;
    private String title;

    private String description;

    private String content;
}
