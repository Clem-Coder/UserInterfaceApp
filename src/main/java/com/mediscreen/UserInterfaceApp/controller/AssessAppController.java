package com.mediscreen.UserInterfaceApp.controller;

import com.mediscreen.UserInterfaceApp.domain.PatientReport;
import com.mediscreen.UserInterfaceApp.proxy.AssessAppProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger("AssessAppController");


    /**
     *
     * Calculates the patient's risk of developing diabetes
     *
     * @param id The id of the Patient
     * @return show the PatientReport in the view
     */


    @GetMapping("/assess/{id}")
    public String getPatientReport(@PathVariable Integer id, Model model){
        logger.info("New request: Get patient report");
        model.addAttribute("patientReport",assessAppProxy.getPatientReport(id)) ;
        return "assess/get";
    }

}
