package com.spring.boot.springbootarticlesystem.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "iD can not be empty")
    private String iD;

    @NotEmpty(message = "title can not be empty")
    @Size(max = 100, message = "title size can not be more than 100 characters")
    private String title;

    @NotEmpty(message = "author can not be empty")
    @Size(min = 5, max = 20, message = "author size must be between 5 to 20 characters")
    private String author;

    @NotEmpty(message = "content can not be empty")
    @Size(min = 201, message = "content size can not be less than 201 characters")
    private String content;

    @NotEmpty(message = "category can not be empty")
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;

    @NotEmpty(message = "image url can not be empty")
    private String imageUrl;

    @AssertFalse(message = "isPublished must initially be false")
    private boolean isPublished;

    @Pattern(regexp = "^\\d\\d\\d\\d/\\d\\d/\\d\\d$", message = "the publishDate must be in the format of YYYY/MM/DD")
    private LocalDate publishDate;

}
