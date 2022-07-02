package com.mediscreen.UserInterfaceApp.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.UserInterfaceApp.domain.Patient;
import com.mediscreen.UserInterfaceApp.proxy.PatientAppProxy;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PatientAppControllerTests {

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


    public String getPatientInJson() throws JsonProcessingException {
        Patient patient = new Patient("test", "test", LocalDate.ofEpochDay(1993) , "M");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ObjectWriter ow  = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(patient );
    }

    @MockBean
    private PatientAppProxy patientAppProxy;

    @Test
    public void getAllPatients_Test() throws Exception {
        List<Patient> patientList = new ArrayList<>();
        when(patientAppProxy.getAllPatients()).thenReturn(patientList);
        mockMvc.perform(get("/patient/list")).andExpect(status().isOk());
    }

    @Test
    public void showAddPatientForm_Test() throws Exception {
        mockMvc.perform(get("/patient/add")).andExpect(status().isOk());
    }

    @Test
    public void AddPatient_Test() throws Exception {
        Patient patient = new Patient();
        doNothing().when(patientAppProxy).addNewPatient(patient);
        mockMvc.perform(post("/patient/validate")
                .contentType(APPLICATION_JSON)
                .content(getPatientInJson())).andExpect(status().isOk());
    }

    @Test
    public void showSearchPatientForm_Test() throws Exception {
        mockMvc.perform(get("/patient/search")).andExpect(status().isOk());
    }

    @Test
    public void getPatientWithFirstnameAndLastname_Test() throws Exception {
        String firstname  = "test";
        String lastname = "test";
        when(patientAppProxy.getPatientByFirstnameAndLastname(firstname, lastname)).thenReturn(new Patient());
        mockMvc.perform(get("/patient/get")
                .param("firstname", firstname)
                .param("lastname", lastname)).andExpect(status().isOk());
    }

    @Test
    public void getPatientWithId_Test() throws Exception {
        Integer id = 1;
        when(patientAppProxy.getPatientById(id)).thenReturn(new Patient());
        mockMvc.perform(get("/patient/get/{id}", id))
               .andExpect(status().isOk());
    }

    @Test
    public void showUpdatePatientForm_Test() throws Exception {
        Integer id = 1;
        when(patientAppProxy.getPatientById(id)).thenReturn(new Patient());
        mockMvc.perform(get("/patient/update/{id}", id)).andExpect(status().isOk());
    }

    @Test
    public void updatePatient_Test() throws Exception {
        Integer id = 1;
        doNothing().when(patientAppProxy).updatePatientInfos(id, new Patient());
        mockMvc.perform(post("/patient/update/{id}", id)
                .contentType(APPLICATION_JSON)
                .content(getPatientInJson())).andExpect(status().isOk());
    }
}
