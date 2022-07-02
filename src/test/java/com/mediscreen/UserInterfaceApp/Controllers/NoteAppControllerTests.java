package com.mediscreen.UserInterfaceApp.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.UserInterfaceApp.domain.Patient;
import com.mediscreen.UserInterfaceApp.domain.PatientNote;
import com.mediscreen.UserInterfaceApp.proxy.NoteAppProxy;
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
public class NoteAppControllerTests {

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


    public String getPatientNoteInJson() throws JsonProcessingException {
        PatientNote patientNote = new PatientNote(1, LocalDate.now(),"Dr test", "test");
        ObjectMapper mapper  = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(patientNote );
    }

    @MockBean
    private NoteAppProxy noteAppProxy;

    @Test
    public void getAllPatientNotes_Test() throws Exception {
        Integer id = 1;
        List<PatientNote> patientNotes = new ArrayList<>();
        when(noteAppProxy.getPatHistory(id)).thenReturn(patientNotes);
        mockMvc.perform(get("/note/list/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    public void AddPatientNote_Test() throws Exception {
        Integer id = 1;
        PatientNote patientNote = new PatientNote(id);
        doNothing().when(noteAppProxy).addPatientNote(id, patientNote);
        mockMvc.perform(post("/note/add/{id}", id)
                .contentType(APPLICATION_JSON)
                .content(getPatientNoteInJson())).andExpect(status().isOk());
    }

}
