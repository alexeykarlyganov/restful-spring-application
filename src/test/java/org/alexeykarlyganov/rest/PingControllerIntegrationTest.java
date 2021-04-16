package org.alexeykarlyganov.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.alexeykarlyganov.rest.controllers.system.PingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = RestApplication.class)
@AutoConfigureMockMvc
public class PingControllerIntegrationTest {
    @Autowired
    private PingController pingController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void controllerShouldLoad()
    {
        assertThat(pingController).isNotNull();
    }

    @Test
    public void shouldReturnNameOfProject() throws Exception {
        this.mockMvc.perform(get("/system/ping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("rest")));
    }

    @Test
    public void shouldContainsBuildAndStartTime() throws Exception {
        this.mockMvc.perform(get("/system/ping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startTime", is(notNullValue())))
                .andExpect(jsonPath("$.buildTime", is(notNullValue())));
    }
}
