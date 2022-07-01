package com.mediscreen.UserInterfaceApp.controller;

import com.mediscreen.UserInterfaceApp.domain.Patient;
import com.mediscreen.UserInterfaceApp.domain.PatientNote;
import com.mediscreen.UserInterfaceApp.proxy.NoteAppProxy;
import com.mediscreen.UserInterfaceApp.proxy.PatientAppProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class NoteAppController {

    @Autowired
    NoteAppProxy noteAppProxy;

    private static final Logger logger = LogManager.getLogger("NoteAppController");


    /**
     *
     * Get all patient's notes
     *
     * @param id The id of the Patient
     * @return show the list of notes in the view
     */


    @GetMapping("/note/list/{id}")
    public String getAllPatientNotes(@PathVariable Integer id, Model model){

        logger.info("New request: show all patient's notes for patient id: " + id);
        List<PatientNote> patientNoteList = noteAppProxy.getPatHistory(id);
        PatientNote patientNote = new PatientNote(id);
        model.addAttribute("patientNote", patientNote);

        if(patientNoteList.isEmpty()){
            model.addAttribute("patientNotesEmpty", "This patient has no notes");
            return "note/list";
        }
        model.addAttribute("patientNotes", noteAppProxy.getPatHistory(id) );
        return "note/list";
    }


    /**
     *
     * Add a new note for the patient
     *
     * @param patientId The id of the Patient
     * @param patientNote the new note for the patient
     *
     * @return show the list of notes in the view with the new note
     */


    @PostMapping ("/note/add/{id}")
    public String showAddForm(@PathVariable (name = "id") Integer patientId, @Valid PatientNote patientNote, BindingResult result, Model model){
        logger.info("New request: show all patient's notes for patient id: " + patientId);
        if(!result.hasErrors()){
            logger.info("New request: add new note for patient id: " + patientId);
            patientNote.setDate(LocalDate.now());
            noteAppProxy.addPatientNote(patientId, patientNote);
            model.addAttribute("patientNotes", noteAppProxy.getPatHistory(patientId));
            model.addAttribute("addSuccess", "Note successfully save");
            return "note/list";
        }
        logger.error("Error: the returned note is invalid");
        model.addAttribute("patientNotes", noteAppProxy.getPatHistory(patientId));
        model.addAttribute("addError", "Error during note recording");
        return "note/list";
    }
}
