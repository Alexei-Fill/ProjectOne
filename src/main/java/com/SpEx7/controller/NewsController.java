package com.SpEx7.controller;

import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewsController {

    private NewsService newsService;

    @Autowired
    @Qualifier("newsService")
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/newsList")
    public String newsList(Model model) {
//        model.addAttribute("news", new News());
        model.addAttribute("newsList", newsService.listNews());
        return "newsList";
    }

    @GetMapping("/showAddNews")
    public String showAddNews(Model model) {
        model.addAttribute("news", new News());
        return "editNews";
    }

    @GetMapping("/showEditNews/{id}")
    public String showEditNews(@PathVariable("id") int id, Model model) {
        model.addAttribute("news", newsService.getNewsById(id));
        return "editNews";
    }

    @PostMapping("/editAddNews")
    public String addEditNews(@ModelAttribute("news") News news) {
        if (news.getId() == 0) {
            newsService.addNews(news);
        } else {
            newsService.updateNews(news);
        }
        return "redirect: /newsList";
    }

    @PostMapping("/deleteNews")
    public String deleteNews(@Nullable @RequestParam("removedNews") List<Integer> id) {
        if (id != null) {
            for (Integer i : id) {
                newsService.deleteNews(i);
            }
        }
        return "redirect: /newsList";
    }

    @GetMapping("/news/{id}")
    public String showNews(@PathVariable("id") int id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news";
    }
}
