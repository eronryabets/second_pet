package com.eronryabets.second_pet;


import com.eronryabets.second_pet.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MainController mainController;

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @Test
    public void test() throws Exception {
        assertThat(mainController).isNotNull();
    }

    @Test
    public void contextLoads() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, guest")))
                .andExpect(content().string(containsString("This a simple Pet Project :)")));

    }

}
