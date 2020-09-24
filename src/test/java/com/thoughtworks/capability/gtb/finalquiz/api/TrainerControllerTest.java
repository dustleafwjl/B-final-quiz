package com.thoughtworks.capability.gtb.finalquiz.api;


import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import com.thoughtworks.capability.gtb.finalquiz.service.TrainerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainerController.class)
@AutoConfigureJsonTesters
class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerService;
    @Autowired
    private JacksonTester<Trainer> trainerJacksonTester;

    @Autowired
    private JacksonTester<List<Trainer>> trainersJacksonTester;

    private Trainer firstTrainer;

    @BeforeEach
    public void beforeEach() {
        firstTrainer = Trainer.builder().name("test").build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(trainerService);
    }

    @Nested
    class createTrainer {
        @Test
        public void should_create_trainer_when_given_new_trainer() throws Exception{
            when(trainerService.createTrainer(firstTrainer)).thenReturn(firstTrainer);
            MockHttpServletResponse response = mockMvc.perform(post("/trainers").content(trainerJacksonTester.write(firstTrainer).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();
                        assertThat(response.getContentAsString()).isEqualTo(
                    trainerJacksonTester.write(firstTrainer).getJson()
            );
            verify(trainerService).createTrainer(firstTrainer);
        }
    }

    @Nested
    class getTrainerByGrouped {
        @Test
        public void should_get_trainers_when_given_grouped() throws Exception {
            List<Trainer> trainers = new ArrayList<>();
            trainers.add(firstTrainer);
            trainers.add(firstTrainer);
            when(trainerService.getTrainerByGrouped(false)).thenReturn(trainers);
            MockHttpServletResponse response = mockMvc.perform(get("/trainers?grouped=false"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    trainersJacksonTester.write(trainers).getJson()
            );
            verify(trainerService).getTrainerByGrouped(false);
        }
    }

    @Nested
    class deleteTrainer {
        @Test
        public void should_return_no_content_when_given_trainer_id() throws Exception {
            List<Trainer> trainers = new ArrayList<>();
            trainers.add(firstTrainer);
            trainers.add(firstTrainer);
            mockMvc.perform(delete("/trainers/{id}", 123))
                    .andExpect(status().isNoContent());
            verify(trainerService).deleteTrainer(123L);
        }
    }
}