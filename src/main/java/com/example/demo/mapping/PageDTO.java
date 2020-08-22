package com.example.demo.mapping;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PageDTO {
    @NotBlank(message = "spring")
    String filter;
    Integer size = 2;
    Integer page = 0;
}
