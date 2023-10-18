package edu.tomerbu.blogfinalproject.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostDto {
    @NotNull
    @Size(min = 4, max = 300)
    private String title;
    @NotNull
    @Size(min = 4, max = 5000)
    private String description;
    @NotNull
    @Size(min = 4)
    private String content;
}
