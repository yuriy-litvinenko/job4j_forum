package ru.job4j.forum.control;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Captor
    private ArgumentCaptor<Post> argument;

    @Test
    @Order(1)
    @WithMockUser
    public void shouldReturnSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ладу-гранта. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        Mockito.verify(postService).save(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Куплю ладу-гранта. Дорого."));
    }

    @Test
    @Order(2)
    @WithMockUser
    public void shouldSaveThenEditThenReturnUpdatePost() throws Exception {
        this.mockMvc.perform(get("/edit/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ауди."))
                .andDo(print())
                .andExpect(status().isFound());
        Mockito.verify(postService).save(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Куплю ауди."));
    }

    @Test
    @Order(3)
    @WithMockUser
    public void shouldReturnDefaultMessageAddPost() throws Exception {
        this.mockMvc.perform(get("/addPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @Order(4)
    @WithMockUser
    public void shouldReturnDefaultMessageSavePost() throws Exception {
        this.mockMvc.perform(post("/save"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    @Order(5)
    @WithMockUser
    public void shouldReturnDefaultMessageGetPost() throws Exception {
        this.mockMvc.perform(get("/post/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @Order(6)
    @WithMockUser
    public void shouldReturnDefaultMessageEditPost() throws Exception {
        this.mockMvc.perform(get("/edit/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }
}
