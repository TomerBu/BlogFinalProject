package edu.tomerbu.blogfinalproject.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeletePostResponseDto {
    private boolean deleted;
    private ResponsePostDto post;
}
