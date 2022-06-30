package com.mediscreen.UserInterfaceApp.controller;

import com.mediscreen.UserInterfaceApp.domain.PatientReport;
import com.mediscreen.UserInterfaceApp.proxy.AssessAppProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@Controller
public class AssessAppController {

    @Autowired
    AssessAppProxy assessAppProxy;


    @GetMapping("/assess/{id}")
    public String getPatientReport(@PathVariable Integer id, Model model){
        model.addAttribute("patientReport",assessAppProxy.getPatientReport(id)) ;
        return "assess/get";
    }

}
