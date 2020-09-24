package com.thoughtworks.capability.gtb.finalquiz.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TraineeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Trainee firstTrainee;

    @BeforeEach
    public void setUp() {
        firstTrainee = Trainee.builder().name("demotes").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build();
    }

    @Test
    public void should_return_created_when_create_trainee_given_new_trainee() throws Exception {
        mockMvc.perform(post("/trainees")
                .content(objectMapper.writeValueAsString(firstTrainee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_return_ok_when_get_given_false_grouped() throws Exception {
        mockMvc.perform(get("/trainees?grouped=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("沈乐棋")))
                .andExpect(jsonPath("$[0].email", is("leqi@demo.com")))
                .andExpect(jsonPath("$[0].github", is("leqi@demo.com")));
    }
}
