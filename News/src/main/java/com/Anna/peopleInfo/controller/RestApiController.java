package com.Anna.peopleInfo.controller;


import com.Anna.peopleInfo.entity.News;
import com.Anna.peopleInfo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {

    private final NewsService newsService;

    @Autowired
    public RestApiController(NewsService newsService){
        this.newsService = newsService;
    }

    @PostMapping("/api/news")
    public ResponseEntity<?> create(@RequestBody News news){
        newsService.create(news);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/news")
    public ResponseEntity<List<News>> findAll(){
        final List<News> newsList = newsService.findAll();
        return newsList != null && !newsList.isEmpty()
                ? new ResponseEntity<>(newsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/news/{id}")
    public ResponseEntity<Optional<News>> find(@PathVariable(name = "id") Long id){
        final Optional<News> news = newsService.find(id);
        return news != null
                ? new ResponseEntity<>(news, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/news/{id}")
    public ResponseEntity<?> updateNews(@PathVariable(name = "id") Long id, @RequestBody News newsUpdate) {
        return newsService.find(id).map(news -> {
            news.setTitle(newsUpdate.getTitle());
            news.setAuthorName(newsUpdate.getTitle());
            news.setAddingDate(newsUpdate.getAddingDate());
            news.setEditingDate(newsUpdate.getEditingDate());
            news.setText(newsUpdate.getText());
            news.setCategoryName(newsUpdate.getCategoryName());
            newsService.update(news);
            return new ResponseEntity<>(news, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/news/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable(name = "id") Long id) {
        return newsService.find(id).map(news -> {
            newsService.delete(news);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }

}
