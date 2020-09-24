package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
import com.thoughtworks.capability.gtb.finalquiz.domain.Trainer;
import com.thoughtworks.capability.gtb.finalquiz.service.TraineeService;
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

@WebMvcTest(TraineeController.class)
@AutoConfigureJsonTesters
class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TraineeService traineeService;
    @Autowired
    private JacksonTester<Trainee> traineeJacksonTester;

    @Autowired
    private JacksonTester<List<Trainee>> traineesJacksonTester;

    private Trainee firstTrainee;

    @BeforeEach
    public void beforeEach() {
        firstTrainee = Trainee.builder().name("demotes").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(traineeService);
    }
    @Nested
    class createTrainee {
        @Test
        public void should_return_new_user_when_given_user_info() throws Exception {
            when(traineeService.createTrainee(firstTrainee)).thenReturn(firstTrainee);
            MockHttpServletResponse response = mockMvc.perform(post("/trainees")
                    .content(traineeJacksonTester.write(firstTrainee).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    traineeJacksonTester.write(firstTrainee).getJson()
            );
            verify(traineeService).createTrainee(firstTrainee);
        }
    }

    @Nested
    class getTraineeByGrouped {
        @Test
        public void should_get_trainers_when_given_grouped() throws Exception {
            List<Trainee> trainees = new ArrayList<>();
            trainees.add(firstTrainee);
            trainees.add(firstTrainee);
            when(traineeService.getTraineeByGrouped(false)).thenReturn(trainees);
            MockHttpServletResponse response = mockMvc.perform(get("/trainees?grouped=false"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse();
            assertThat(response.getContentAsString()).isEqualTo(
                    traineesJacksonTester.write(trainees).getJson()
            );
            verify(traineeService).getTraineeByGrouped(false);
        }
    }

    @Nested
    class deleteTrainee {
        @Test
        public void should_return_no_content_when_given_trainer_id() throws Exception {
            List<Trainee> trainees = new ArrayList<>();
            trainees.add(firstTrainee);
            trainees.add(firstTrainee);
            mockMvc.perform(delete("/trainees/{id}", 123))
                    .andExpect(status().isNoContent());
            verify(traineeService).deleteTrainee(123L);
        }
    }
}