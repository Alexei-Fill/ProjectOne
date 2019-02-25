package com.SpEx7.controller;

import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewsController {
    private final static String REDIRECT_TO_MAIN = "redirect: /newsList";
    private final static String TO_EDIT_NEWS = "editNews";

    @Autowired
    private NewsService newsService;

    @GetMapping("/newsList")
    public String newsList(Model model) {
        model.addAttribute("newsList", newsService.listNews());
        return "newsList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/showAddNews")
    public String showAddNews(Model model) {
        model.addAttribute("news", new News());
        return TO_EDIT_NEWS;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/showEditNews/{id}")
    public String showEditNews(@PathVariable("id") int id, Model model) {
        model.addAttribute("news", newsService.getNewsById(id));
        return TO_EDIT_NEWS;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editAddNews")
    public String addEditNews(@Validated @ModelAttribute("news") News news, BindingResult result) {
        if (result.hasErrors()){
            return TO_EDIT_NEWS;
        }else {
            if (news.getId() == 0) {
                newsService.addNews(news);
            } else {
                newsService.updateNews(news);
            }
            return REDIRECT_TO_MAIN;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/deleteNews")
    public String deleteNews(@Nullable @RequestParam("removedNews") List<Integer> id) {
        if (id != null) {
            newsService.deleteNews(id);
        }
        return REDIRECT_TO_MAIN;
    }

    @GetMapping("/news/{id}")
    public String showNews(@PathVariable("id") int id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news";
    }
}
