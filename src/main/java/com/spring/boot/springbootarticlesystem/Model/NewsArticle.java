package com.spring.boot.springbootarticlesystem.Model;

import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^(politics|sports|technology)$", message = "The category must be one of the following: " +
            "politics or sports or technology")
    private String category;

    @NotEmpty(message = "image url can not be empty")
    private String imageUrl;

    @AssertFalse(message = "isPublished must initially be false")
    private boolean isPublished;

    // since isPublished must be false at first, it makes no sense to set the publishDate when creating the article
    // hence it does not need validation except when updating the article
    @PastOrPresent(message = "the publishDate must be in the past or the present")
    private LocalDate publishDate;

}
