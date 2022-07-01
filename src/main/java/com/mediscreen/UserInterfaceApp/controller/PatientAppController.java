package com.mediscreen.UserInterfaceApp.controller;

import com.mediscreen.UserInterfaceApp.domain.Patient;
import com.mediscreen.UserInterfaceApp.proxy.PatientAppProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PatientAppController {

    @Autowired
    PatientAppProxy patientAppProxy;

    private static final Logger logger = LogManager.getLogger("PatientAppController");


    /**
     * Get all patients
     *
     * @return show the list of patient in the view
     */

    @GetMapping("/patient/list")
    public String getAllPatients(Model model){
        logger.info("New request: show all patients in the view");
        model.addAttribute("patients", patientAppProxy.getAllPatients());
        return "patient/list";
    }

    /**
     * Get form to register a new patient
     *
     * @return show the form to register a new patient in the view
     */

    @GetMapping("/patient/add")
    public String showAddForm(Model model){
        logger.info("New request: show add patient form in the view");
        model.addAttribute("patient", new Patient());
        return "patient/add";
    }



    /**
     * Register a new patient
     *
     * @param patient the patient to register
     * @return show the new patient medical folder in the view
     */

    @PostMapping("/patient/validate")
    public String addPatient(@Valid Patient patient, BindingResult result, Model model){
        logger.info("New request: register a new patient");
        if (!result.hasErrors()){
            patientAppProxy.addNewPatient(patient);
            model.addAttribute("addSuccess", "Patient successfully save");
            model.addAttribute("patient", patientAppProxy.getPatientByFirstnameAndLastname(patient.getFirstname(), patient.getLastname()));
            return "patient/infos";
        }
        logger.error("New request: the returned patient is invalid");
        return "patient/add";
    }


    /**
     * Get form to find a patient
     *
     * @return show the form to search a patient with firstname and lastname
     */

    @GetMapping("/patient/search")
    public String searchPatient(){
        logger.info("New request: show find patient form in the view");
        return "patient/search";
    }

    @GetMapping("/patient/get")
    public String getPatientByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname, Model model){
        logger.info("New request: find a patient in db with firstname: " + firstname + ", lastname: " + lastname);
        Patient patient = patientAppProxy.getPatientByFirstnameAndLastname(firstname,lastname);
        if(patient != null){
            logger.info("Patient found");
            model.addAttribute("patient", patient );
            return "patient/infos";
        }
        logger.error("No patient found");
        model.addAttribute("patientError", "no patients found");
        return "patient/search";
    }


    /**
     * Get patient medical folder
     *
     * @param id The id of the Patient
     * @return show the patient medical folder in the view
     */


    @GetMapping("/patient/get/{id}")
    public String getPatientById (@PathVariable Integer id, Model model){
        logger.info("New request: find a patient by id with id :" + id);
        model.addAttribute("patient", patientAppProxy.getPatientById(id));
        return "patient/infos";
    }

    /**
     * Get form to update patient infos
     *
     * @param id The id of the Patient
     * @return show the form with all patient infos you can modify in the view
     */

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm (@PathVariable Integer id, Model model){
        logger.info("New request: show patient with id :" + id + " information for updating");
        model.addAttribute("patient", patientAppProxy.getPatientById(id));
        return "patient/update";
    }

    /**
     * Update patient infos
     *
     * @param id The id of the Patient
     * @param patient The patient with information updated
     *
     * @return show the patient infos updated in the view
     */

    @PostMapping ("/patient/update/{id}")
    public String updatePatientInfos(@PathVariable Integer id, @Valid Patient patient, BindingResult result, Model model){
        logger.info("New request: update patient with id :" + id + " information");
        if(!result.hasErrors()){
            patient.setId(id);
            patientAppProxy.updatePatientInfos(id, patient);
            model.addAttribute("patient", patient);
            model.addAttribute("updateSuccess", "Patient information successfully updated");
            return "patient/infos";
        }
        logger.error("patient information returned are not valid");

        return "patient/update";
    }




}
