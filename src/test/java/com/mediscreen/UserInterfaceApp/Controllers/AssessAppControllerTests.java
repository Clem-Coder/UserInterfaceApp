package com.mediscreen.UserInterfaceApp.Controllers;


import com.mediscreen.UserInterfaceApp.domain.PatientNote;
import com.mediscreen.UserInterfaceApp.domain.PatientReport;
import com.mediscreen.UserInterfaceApp.proxy.AssessAppProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AssessAppControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void printApplicationContext() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Arrays.stream(webApplicationContext.getBeanDefinitionNames())
              .map(name -> webApplicationContext.getBean(name).getClass().getName())
              .sorted()
              .forEach(System.out::println);
    }

    @MockBean
    AssessAppProxy assessAppProxy;


    @Test
    public void getPatientReport_Test() throws Exception {
        Integer id = 1;
        PatientReport patientReport = new PatientReport();
        when(assessAppProxy.getPatientReport(id)).thenReturn(patientReport);
        mockMvc.perform(get("/assess/{id}", 1)).andExpect(status().isOk());
    }

}
