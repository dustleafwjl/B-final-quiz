package com.thoughtworks.capability.gtb.finalquiz.api;

import com.thoughtworks.capability.gtb.finalquiz.domain.Trainee;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private Trainee firstTrainee;

    @BeforeEach
    public void beforeEach() {
        firstTrainee = Trainee.builder().name("沈乐棋").email("leqi@demo.com").github("leqi@demo.com").office("wuhan").build();
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
            MockHttpServletResponse response = mockMvc.perform(post("/trainees").content(traineeJacksonTester.write(firstTrainee).getJson())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn()
                    .getResponse();
//            assertThat(response.getContentAsString()).isEqualTo(
//                    traineeJacksonTester.write(firstTrainee).getJson()
//            );
            verify(traineeService).createTrainee(firstTrainee);
        }
    }

    @Nested
    class getTraineeByGrouped {
    }
}