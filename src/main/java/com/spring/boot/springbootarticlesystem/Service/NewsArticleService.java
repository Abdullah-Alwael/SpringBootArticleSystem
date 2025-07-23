package com.spring.boot.springbootarticlesystem.Service;

import com.spring.boot.springbootarticlesystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle> articles = new ArrayList<>();

    public ArrayList<NewsArticle> getAllArticles() {
        return articles;
    }

    public void addArticle(NewsArticle article) {
        articles.add(article);
    }

    public boolean updateArticle(String iD, NewsArticle article) {
        article.setID(iD); // to prevent user from changing the iD
        for (NewsArticle a : articles) {
            if (a.getID().equals(iD)) {
                articles.set(articles.indexOf(a), article);
                return true;
            }
        }

        return false;
    }

    public boolean deleteArticle(String iD) {
        for (NewsArticle a : articles) {
            if (a.getID().equals(iD)) {
                articles.remove(a);
                return true;
            }
        }

        return false;
    }

    public boolean publishArticle(String iD) {
        for (NewsArticle a : articles) {
            if (a.getID().equals(iD)) {
                a.setPublished(true);
                a.setPublishDate(LocalDate.now()); // sets the publishDate to the current date automatically
                return true;
            }
        }

        return false;
    }

    public ArrayList<NewsArticle> getPublishedArticles() {
        ArrayList<NewsArticle> publishedArticles = new ArrayList<>();

        for (NewsArticle a : articles) {
            if (a.isPublished()) {
                publishedArticles.add(a);
            }
        }

        return publishedArticles;
    }

    public ArrayList<NewsArticle> searchArticlesByCategory(String category) {
        ArrayList<NewsArticle> categorizedArticles = new ArrayList<>();
        // politics|sports|technology
        for (NewsArticle a : articles) {
            if (a.getCategory().equals(category)) {
                categorizedArticles.add(a);
            }
        }

        return categorizedArticles;
    }
}
