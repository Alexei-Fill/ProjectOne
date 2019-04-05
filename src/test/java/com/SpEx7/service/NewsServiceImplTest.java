package com.SpEx7.service;

import com.SpEx7.DAO.NewsDAO;
import com.SpEx7.entity.News;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @Mock
    private NewsDAO newsDAO;

    @InjectMocks
    private NewsServiceImpl newsService ;
    @Captor
    ArgumentCaptor<News> newsCaptor;

    @Test
    public void testGetNewsById_whenIdIsExist() {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());
        given(newsDAO.getNewsById(1)).willReturn(testNews);

        News result = newsService.getNewsById(1);

        then(newsDAO).should(times(1)).getNewsById(1);
        assertEquals(testNews, result);
        assertThat(testNews, equalTo(result));
        assertThat(testNews, notNullValue());
    }

    @Test
    public void testAddNews_whenNewsIsFullNewsObject() {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());

        newsService.addNews(testNews);

        verify(newsDAO).addNews(newsCaptor.capture());
        assertEquals(testNews.getId(), newsCaptor.getValue().getId());
    }

    @Test
    public void testAddNews_whenNewsIsNull() {

        newsService.addNews(null);

        verify(newsDAO, times(1)).addNews(newsCaptor.capture());
        assertNull(newsCaptor.getValue());
    }

    @Test
    public void testUpdateNews_whenNewsIsFullNewsObject() {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());

        newsService.updateNews(testNews);

        verify(newsDAO).updateNews(newsCaptor.capture());
        assertEquals(testNews, newsCaptor.getValue());
    }

    @Test
    public void testUpdateNews_whenNewsIsNull() {

        newsService.updateNews(null);

        verify(newsDAO).updateNews(newsCaptor.capture());
        assertNull(newsCaptor.getValue());
    }

    @Test
    public void testDeleteNews_whenIdIsFullList() {
        List<Integer> testId = new ArrayList<>(Arrays.asList(1, 2, 3));

        newsService.deleteNews(testId);

        verify(newsDAO, times(testId.size())).deleteNews(anyInt());
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteNews_whenIdIsNull() {

        newsService.deleteNews(null);

        verify(newsDAO).deleteNews(anyInt());
    }

    @Test
    public void tesListNews_whenListNewsIsExist() {
        List<News> testNewsList = new ArrayList<>(Arrays.asList(
                new News(1, "Test title1", "Test brief1", "Test content1", LocalDate.now()),
                new News(2, "Test title2", "Test brief2", "Test content2", LocalDate.now()),
                new News(3, "Test title3", "Test brief3", "Test content3", LocalDate.now()),
                new News(4, "Test title4", "Test brief4", "Test content4", LocalDate.now()),
                new News(5, "Test title5", "Test brief5", "Test content5", LocalDate.now()),
                new News(6, "Test title6", "Test brief6", "Test content6", LocalDate.now())
        ));
        when(newsDAO.listNews()).thenReturn(testNewsList);

        List<News> resultNewsList = newsService.listNews();

        assertEquals(testNewsList, resultNewsList);
    }
}
