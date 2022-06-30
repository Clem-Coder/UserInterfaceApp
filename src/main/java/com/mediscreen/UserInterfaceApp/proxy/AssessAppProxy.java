package com.mediscreen.UserInterfaceApp.proxy;

import com.mediscreen.UserInterfaceApp.domain.PatientReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient ( contextId = "Assess", name="assessApp", url="${assessAppUrl}")
public interface AssessAppProxy {

    @GetMapping("/assess/{id}")
    public PatientReport getPatientReport(@PathVariable Integer id);
}
