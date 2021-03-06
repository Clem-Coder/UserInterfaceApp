package com.mediscreen.UserInterfaceApp.proxy;

import com.mediscreen.UserInterfaceApp.domain.PatientNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient( contextId = "Note", name="noteApp", url="${noteAppUrl}")
public interface NoteAppProxy {

    @GetMapping("/patHistory/list/{id}")
    public List<PatientNote> getPatHistory(@PathVariable Integer id);

    @GetMapping("/patHistory/notes/get/{id}")
    public List<String> getNotesById(@PathVariable Integer id);

    @PostMapping("/patHistory/add/{id}")
    public void addPatientNote(@PathVariable Integer id, @RequestBody PatientNote patientNote);
}
