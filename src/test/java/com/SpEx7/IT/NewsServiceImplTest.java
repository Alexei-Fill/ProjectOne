package com.SpEx7.IT;

import com.SpEx7.config.AppConfig;
import com.SpEx7.config.WebSecurityConfig;
import com.SpEx7.entity.News;
import com.SpEx7.service.NewsService;
import com.SpEx7.service.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AppConfig.class, WebSecurityConfig.class})
public class NewsServiceImplTest {

    @Autowired
    NewsService newsService;

    @Captor
    ArgumentCaptor<News> newsArgumentCaptor;

    @Before
    public void setUp(){

    }

    @Test
    public void addNews() {

    }

    @Test
    public void updateNews() {
    }

    @Test
    public void listNews() {
    }

    @Test
    public void getNewsById() {
        int id = 179;

        News newsById = newsService.getNewsById(id);

        assertEquals(id, newsById.getId());
    }

    @Test
    public void deleteNews() {
    }
}
