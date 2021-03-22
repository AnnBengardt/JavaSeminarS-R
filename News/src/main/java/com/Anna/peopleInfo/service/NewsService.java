package com.Anna.peopleInfo.service;

import com.Anna.peopleInfo.entity.News;
import com.Anna.peopleInfo.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepo newsRepo;

    public void create(News news){
        newsRepo.save(news);
    }

    public void update(News news) { newsRepo.save(news); }

    public void delete(News news) { newsRepo.delete(news); }

    public List<News> findAll(){
        return newsRepo.findAll();
    }

    public Optional<News> find(Long id){
        return newsRepo.findById(id);
    }

}
