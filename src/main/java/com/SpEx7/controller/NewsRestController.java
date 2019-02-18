package com.SpEx7.controller;

import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value = "/rest/news")
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


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News addNews(@Validated @RequestBody News news) {
        newsService.addNews(news);
        return news;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public News editNews(@Validated @RequestBody News news) {
        newsService.updateNews(news);
        return news;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteNews(@RequestBody Integer[] id) {
        if (id != null) {
            newsService.deleteNews(Arrays.asList(id));
        }
    }
}
