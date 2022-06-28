package com.mediscreen.UserInterfaceApp.controller;

import com.mediscreen.UserInterfaceApp.domain.Patient;
import com.mediscreen.UserInterfaceApp.proxy.PatientAppProxy;
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

    @GetMapping("/patient/list")
    public String getAllPatients(Model model){
        model.addAttribute("patients", patientAppProxy.getAllPatients());
        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String showAddForm(Model model){
        model.addAttribute("patient", new Patient());
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String addPatient(@Valid Patient patient, BindingResult result, Model model){
        if (!result.hasErrors()){
            patientAppProxy.addNewPatient(patient);
            model.addAttribute("addSuccess", "Patient successfully save");
            model.addAttribute("patient", patientAppProxy.getPatientByFirstnameAndLastname(patient.getFirstname(), patient.getLastname()));
            return "patient/infos";
        }
        return "patient/add";
    }


    @GetMapping("/patient/search")
    public String searchPatient(){
        return "patient/search";
    }

    @GetMapping("/patient/get")
    public String getPatientByFirstnameAndLastname(@RequestParam String firstname, @RequestParam String lastname, Model model){
        Patient patient = patientAppProxy.getPatientByFirstnameAndLastname(firstname,lastname);
        if(patient != null){
            model.addAttribute("patient", patient );
            return "patient/infos";
        }
        model.addAttribute("patientError", "no patients found");
        return "patient/search";
    }

    @GetMapping("/patient/get/{id}")
    public String getPatientById (@PathVariable Integer id, Model model){
        model.addAttribute("patient", patientAppProxy.getPatientById(id));
        return "patient/infos";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm (@PathVariable Integer id, Model model){
        model.addAttribute("patient", patientAppProxy.getPatientById(id));
        return "patient/update";
    }

    @PostMapping ("/patient/update/{id}")
    public String updatePatientInfos(@PathVariable Integer id, @Valid Patient patient, BindingResult result, Model model){

        if(!result.hasErrors()){
            patient.setId(id);
            patientAppProxy.updatePatientInfos(id, patient);
            model.addAttribute("patient", patient);
            model.addAttribute("updateSuccess", "Patient information successfully updated");
            return "patient/infos";
        }
        return "patient/update";
    }




}
