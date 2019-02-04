package com.SpEx7.controller;

import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/newsR")
public class NewsRestController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public List<News> newsList() {
        return newsService.listNews();
    }

    @GetMapping("/{id}")
    public News showNews(@PathVariable("id") int id) {
        return newsService.getNewsById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News addNews(@RequestBody News news) {
        newsService.addNews(news);
        return news;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public News editNews(@RequestBody News news) {
        newsService.updateNews(news);
        return news;
    }

    @DeleteMapping
    public void deleteNews(@RequestBody Integer[] id) {
        if (id != null) {
            newsService.deleteNews(Arrays.asList(id));
        }
    }
}
