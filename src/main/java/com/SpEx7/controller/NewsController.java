package com.SpEx7.controller;

import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @RequestMapping("/newsList")
    @GetMapping
    public String newsList(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("newsList", newsService.listNews());
        return "newsList";
    }

    @RequestMapping("/showAddNews")
    @GetMapping
    public String showAddNews(Model model) {
        model.addAttribute("news", new News());
        return "editNews";
    }

    @RequestMapping("/showEditNews/{id}")
    @GetMapping
    public String showEditNews(@PathVariable("id") int id, Model model) {

            News news = newsService.getNewsById(id);
            model.addAttribute("news", news);

        return "editNews";
    }

    @RequestMapping("/editAddNews")
    @PostMapping
    public String addEditNews(@ModelAttribute("news") News news) {
        if (news.getId() == 0) {
            newsService.addNews(news);
        } else {
            newsService.updateNews(news);
        }
        return "redirect: /newsList";
    }

    @RequestMapping("/deleteNews")
    @PostMapping
    public String deleteNews(@RequestParam("removedNews") List<Integer> id) {
        for (Integer i: id) {
            newsService.deleteNews(i);
        }
        return "redirect: /newsList";
    }

    @RequestMapping("/news/{id}")
    @GetMapping
    public String showNews(@PathVariable("id") int id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news";
    }
}
