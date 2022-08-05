package io.github.gabriel.andrad3.quarkussocial.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePostRequest {
    @NotBlank
    private String text;
}
