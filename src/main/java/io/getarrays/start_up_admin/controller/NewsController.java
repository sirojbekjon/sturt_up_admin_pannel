package io.getarrays.start_up_admin.controller;

import io.getarrays.start_up_admin.payload.NewsDto;
import io.getarrays.start_up_admin.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @PostMapping("/add")
    public HttpEntity<?> addNews(@RequestBody NewsDto newsDto){
        return newsService.addNews(newsDto);
    }

    @GetMapping("/get")
    public HttpEntity<?> getNews(){
        return newsService.getNews();
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editNews(@PathVariable Long id, @RequestBody NewsDto newsDto){
        return newsService.editNews(id, newsDto);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteNewsById(@PathVariable Long id){
        return newsService.deleteNews(id);
    }
}