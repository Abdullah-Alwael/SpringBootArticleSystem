package com.spring.boot.springbootarticlesystem.Controller;

import com.spring.boot.springbootarticlesystem.Api.ApiResponse;
import com.spring.boot.springbootarticlesystem.Model.NewsArticle;
import com.spring.boot.springbootarticlesystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/article")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/list")
    public ResponseEntity<?> getArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getAllArticles());
    }

    @PostMapping("/new")
    public ResponseEntity<?> addArticle(@Valid @RequestBody NewsArticle article, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }

        newsArticleService.addArticle(article);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article was added successfully"));
    }

    @PutMapping("/update/{iD}")
    public ResponseEntity<?> updateArticle(@PathVariable String iD,
                                           @Valid @RequestBody NewsArticle article, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (newsArticleService.updateArticle(iD, article)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article was updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, article was not found"));
        }

    }

    @DeleteMapping("/delete/{iD}")
    public ResponseEntity<?> deleteArticle(@PathVariable String iD) {
        if (newsArticleService.deleteArticle(iD)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article was deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, article was not found"));
        }
    }

    @PutMapping("publish/{iD}")
    public ResponseEntity<?> publishArticle(@PathVariable String iD) {
        if (newsArticleService.publishArticle(iD)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Article was published successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error, article was not found"));
        }

    }

    @GetMapping("filter/published")
    public ResponseEntity<?> listPublishedArticles() {
        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getPublishedArticles());
    }

    @GetMapping("filter/category/{category}")
    public ResponseEntity<?> filterByCategory(@PathVariable String category) {
        if (!category.matches("^(politics|sports|technology)$")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
                    ApiResponse("The category must be one of the following: politics or sports or technology"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                newsArticleService.searchArticlesByCategory(category));
    }

}
