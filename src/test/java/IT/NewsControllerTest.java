package IT;

import com.SpEx7.config.AppConfig;
import com.SpEx7.config.WebMvcConfig;
import com.SpEx7.config.WebSecurityConfig;
import com.SpEx7.controller.NewsController;
import com.SpEx7.entity.News;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletContext;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AppConfig.class, WebSecurityConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class NewsControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    NewsController newsController;


    News testNews = new News(190, "Test title", "Test brief", "Test content", LocalDate.now());

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesNewsController() {
        ServletContext servletContext = wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("newsController"));
    }

    @Test
    public void NewsList_newsListExist_returnNewsList() throws Exception {

        mockMvc.perform(get("/newsList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("newsList"));
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
        mockMvc.perform(get("/showEditNews/{id}", 179))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editNews"));
    }

    @Test
    public void AddNews_allParametersFilled_addNewsAndRedirectToMain() throws Exception {

        mockMvc.perform(post("/editAddNews")
                .param("id", String.valueOf(testNews.getId()))
                .param("title","testtesttesttesttesttesttesttesttst")
                .param("content", "testtesttesttesttesttesttesttesttesttesttesttesttesttest")
                .param("brief", "testtesttesttesttesttesttesttest")
                .param("date", String.valueOf(LocalDate.now())))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
    }

    @Test
    public void EditNews_allParametersFilled_addNewsAndRedirectToMain() throws Exception {


        mockMvc.perform(post("/editAddNews")
                .param("id", String.valueOf(testNews.getId()))
                .param("title",testNews.getTitle())
                .param("content", testNews.getContent())
                .param("brief", testNews.getBrief())
                .param("date", String.valueOf(testNews.getDate())))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
    }

    @Test
    public void DeleteNews_removedNewsIdsExist_deleteNewsAndRedirectToMain() throws Exception {
        mockMvc.perform(post("/deleteNews")
                .param("removedNews", "179"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
    }

    @Test
    public void DeleteNews_removedNewsDoesNotExist_RedirectToMain() throws Exception {
        mockMvc.perform(post("/deleteNews"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /newsList"));
    }

    @Test
    public void ShowNews_NewsByIdExist_returnViewNewsAndNews() throws Exception {
        mockMvc.perform(get("/news/{id}", 190))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("news"))
                .andExpect(model().attribute("news", testNews));
    }

    @Test(expected = NestedServletException.class)
    public void ShowNews_NewsDoesNotExist_returnViewNewsAndNews() throws Exception {
        News testNews = new News(1, "Test title", "Test brief", "Test content", LocalDate.now());

        mockMvc.perform(get("/news/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("news"))
                .andExpect(model().attributeDoesNotExist("news"));
    }
}
