package com.SpEx7.controller;

import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import com.SpEx7.service.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class NewsControllerTest {

    @InjectMocks
    private NewsController newsController = new NewsController();
    private NewsService newsService = mock(NewsServiceImpl.class);
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        this.mockMvc = standaloneSetup(newsController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void NewsList_newsListExist_returnNewsList() throws Exception {
        List<News> testListNews = new ArrayList<>(Arrays.asList(
                        new News(1, "Test title1", "Test brief1", "Test content1", LocalDate.now()),
                        new News(2, "Test title2", "Test brief2", "Test content2", LocalDate.now()),
                        new News(3, "Test title3", "Test brief3", "Test content3", LocalDate.now())));

        doReturn(testListNews).when(newsService).listNews();

        mockMvc.perform(get("/newsList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("newsList"))
                .andExpect(model().attribute("newsList", testListNews));
    }

    @Test
    public void ShowAddNews_returnViewAndNewNews() throws Exception {
        mockMvc.perform(get("/showAddNews"))
                .andExpect(status().isOk())
                .andExpect(view().name("editNews"))
                .andExpect(model().attributeExists("news"));
    }

    @Test
    public void ShowEditNews_returnViewAndNews() throws Exception {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());

        doReturn(testNews).when(newsService).getNewsById(1);

        mockMvc.perform(get("/showEditNews/{id}", testNews.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("editNews"))
                .andExpect(model().attribute("news", testNews));
    }

    @Test
    public void AddNews_allParametersFilled_addNewsAndRedirectToMain() throws Exception {
        News testNews = new News(0, "Test title", "Test brief", "Test content", LocalDate.now());

        mockMvc.perform(post("/editAddNews")
                .param("id", String.valueOf(testNews.getId()))
                .param("title","")
                .param("content", "")
                .param("brief", testNews.getBrief())
                .param("date", String.valueOf(testNews.getDate())))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
        verify(newsService).addNews(any(News.class));
    }

    @Test
    public void EditNews_allParametersFilled_addNewsAndRedirectToMain() throws Exception {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());

        mockMvc.perform(post("/editAddNews")
                .param("id", String.valueOf(testNews.getId()))
                .param("title",testNews.getTitle())
                .param("content", testNews.getContent())
                .param("brief", testNews.getBrief())
                .param("date", String.valueOf(testNews.getDate())))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
        verify(newsService).updateNews(any(News.class));
    }

    @Test
    public void DeleteNews_removedNewsIdsExist_deleteNewsAndRedirectToMain() throws Exception {
        mockMvc.perform(post("/deleteNews")
                .param("removedNews", "1, 2, 3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
        verify(newsService, times(1)).deleteNews(anyList());
    }

    @Test
    public void DeleteNews_removedNewsDoesNotExist_RedirectToMain() throws Exception {
        mockMvc.perform(post("/deleteNews"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
        verify(newsService, times(0)).deleteNews(anyList());
    }

    @Test
    public void ShowNews_NewsByIdExist_returnViewNewsAndNews() throws Exception {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());
        doReturn(testNews).when(newsService).getNewsById(1);

        mockMvc.perform(get("/news/{id}", testNews.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("news"))
                .andExpect(model().attribute("news", testNews));
    }

    @Test
    public void ShowNews_NewsDoesNotExist_returnViewNewsAndNews() throws Exception {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());
        doReturn(testNews).when(newsService).getNewsById(1);

        mockMvc.perform(get("/news/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(view().name("news"))
                .andExpect(model().attributeDoesNotExist("news"));
    }
}
